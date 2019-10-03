package org.firstinspires.ftc.teamcode;

public interface Autonomous_Interface {
    public void driveForward(double power, int TargetPosition, long timeMs);

    public void driveBack(double power, int TargetPosition, long timeMs);

    public void turnRight(double power, int TargetPosition, long timeMs);

    public void turnLeft(double power, int TargetPosition, long timeMs);

    public void raiseClaw(double power, int TargetPosition, long timeMs);

    public void lowerClaw(double power, int TargetPosition, long timeMs);
}
