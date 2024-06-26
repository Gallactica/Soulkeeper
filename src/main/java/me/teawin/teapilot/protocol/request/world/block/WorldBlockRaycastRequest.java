package me.teawin.teapilot.protocol.request.world.block;

import me.teawin.teapilot.protocol.Response;
import me.teawin.teapilot.protocol.request.world.WorldRaycastRequest;
import me.teawin.teapilot.protocol.response.player.RaycastResponse;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.Nullable;

public class WorldBlockRaycastRequest extends WorldRaycastRequest {
    @Override
    public @Nullable Response call() throws Exception {
        RaycastResponse response = new RaycastResponse();
        HitResult hitResult = raycastBlock(getPoints());
        setBlockResult(hitResult, response);
        return response;
    }
}