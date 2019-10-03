package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AutonomousFunctions implements Autonomous_Interface {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor slide;
    private Servo hook1;
    private Servo hook2;

    boolean ClawMax = false;
    boolean ClawMin = false;

    final int slideMax = 2280;

    public AutonomousFunctions( HardwareMap hardwareMap){
        super();
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void driveForward(double power, int TargetPosition, long timeMs) {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(TargetPosition);
        rightFront.setTargetPosition(TargetPosition);
        leftBack.setTargetPosition(TargetPosition);
        rightBack.setTargetPosition(TargetPosition);

        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void driveBack(double power, int TargetPosition, long timeMs) {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-TargetPosition);
        rightFront.setTargetPosition(-TargetPosition);
        leftBack.setTargetPosition(-TargetPosition);
        rightBack.setTargetPosition(-TargetPosition);

        leftFront.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        rightBack.setPower(-power);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnRight(double power, int TargetPosition, long timeMs) {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(TargetPosition);
        rightFront.setTargetPosition(-TargetPosition);
        leftBack.setTargetPosition(TargetPosition);
        rightBack.setTargetPosition(-TargetPosition);

        leftFront.setPower(power);
        rightFront.setPower(-power);
        leftBack.setPower(power);
        rightBack.setPower(-power);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnLeft(double power, int TargetPosition, long timeMs) {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-TargetPosition);
        rightFront.setTargetPosition(TargetPosition);
        leftBack.setTargetPosition(-TargetPosition);
        rightBack.setTargetPosition(TargetPosition);

        leftFront.setPower(-power);
        rightFront.setPower(power);
        leftBack.setPower(-power);
        rightBack.setPower(power);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void raiseClaw(double power, int TargetPosition, long timeMs) {
        int currentSlidePosition = slide.getCurrentPosition();
        if ((currentSlidePosition + (TargetPosition - currentSlidePosition)) > slideMax)
        {
            //Do Nothing
        }
        else
            slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            slide.setTargetPosition(TargetPosition);

            slide.setPower(power);

            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }


    @Override
    public void lowerClaw(double power, int TargetPosition, long timeMs) {
        int currentSlidePosition = slide.getCurrentPosition();
        if ((currentSlidePosition + (TargetPosition - currentSlidePosition)) < 0)
        {
            //Do Nothing
        }
        else
            slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            slide.setTargetPosition(TargetPosition);

            slide.setPower(-power);

            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

}