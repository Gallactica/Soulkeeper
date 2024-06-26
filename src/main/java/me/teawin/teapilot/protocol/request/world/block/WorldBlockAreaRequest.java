package me.teawin.teapilot.protocol.request.world.block;

import me.teawin.teapilot.protocol.Request;
import me.teawin.teapilot.protocol.Response;
import me.teawin.teapilot.protocol.response.world.WorldBlockAreaResponse;
import me.teawin.teapilot.protocol.type.BlockWithPos;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorldBlockAreaRequest extends Request {

    private boolean noAir;
    private BlockPos position;
    private int radius;
    private boolean relative;
    private BlockPos start;
    private BlockPos end;

    @Override
    public @Nullable Response call() {
        var world = MinecraftClient.getInstance().world;
        assert world != null;

        if (relative) {
            assert MinecraftClient.getInstance().player != null;
            position = MinecraftClient.getInstance().player.getBlockPos();
        }

        if (position != null) {
            if (radius > 0) {
                start = new BlockPos(-radius, -radius, -radius);
                end = new BlockPos(radius, radius, radius);
            }
            start = start.add(position);
            end = end.add(position);
        }

        assert start != null;
        assert end != null;

        List<BlockWithPos> blocks = new ArrayList<>();

        for (BlockPos blockPos : BlockPos.iterate(start, end)) {
            var blockState = world.getBlockState(blockPos);
            if (noAir && (blockState.getBlock().equals(Blocks.AIR) || blockState.getBlock().equals(Blocks.VOID_AIR)))
                continue;
            blocks.add(new BlockWithPos(blockPos.toImmutable(), blockState));
        }

        return new WorldBlockAreaResponse(blocks);
    }
}
