package org.firstinspires.ftc.teamcode;

public interface Autonomous_Interface {
    public void driveForward(double inches, double power);

    public void driveBack(double inches, double power);

    public void turnRight(double inches, double power);

    public void turnLeft(double inches, double power);

    public void moveRight(double inches, double power);

    public void moveLeft(double inches, double power);

    public void raiseClaw(double power, int TargetPosition, long timeMs);

    public void lowerClaw(double power, int TargetPosition, long timeMs);
}
