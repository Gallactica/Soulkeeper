package me.teawin.teapilot.protocol.request.player.movement;

import me.teawin.teapilot.proposal.Movement;
import me.teawin.teapilot.protocol.Request;
import me.teawin.teapilot.protocol.Response;
import org.jetbrains.annotations.Nullable;

public class PlayerMovementRequest extends Request {
    int duration;
    boolean jumping;
    boolean sneaking;
    boolean sprinting;
    float x;
    float y;

    @Override
    public @Nullable Response call() throws Exception {
        Movement.setMovement(x, y, sneaking, jumping, sprinting, duration);
        return null;
    }
}
