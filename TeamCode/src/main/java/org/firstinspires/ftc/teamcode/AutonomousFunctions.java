package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import static java.lang.Thread.sleep;

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
    boolean stoptime = false;

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

    //Old Code (Moving)
    //leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    //leftFront.setTargetPosition(-TargetPosition);
    //rightFront.setTargetPosition(-TargetPosition);
    //leftBack.setTargetPosition(-TargetPosition);
    //rightBack.setTargetPosition(-TargetPosition);

        //leftFront.setPower(power);
        //rightFront.setPower(-power);
        //leftBack.setPower(power);
        //rightBack.setPower(-power);


    //leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    //int currentMotorPosition = rightBack.getCurrentPosition();
    //    if (currentMotorPosition <= -TargetPosition)
    //{
        //leftFront.setPower(0);
        //rightFront.setPower(0);
        //leftBack.setPower(0);
        //rightBack.setPower(0);
    //}

    while(gamepad2.left_stick_y > 0.1){
        int currentSlidePosition = slide.getCurrentPosition();
        if (currentSlidePosition >= slideMax)
        {
            slide.setPower(0);
        }
        else {
            raiseSlide(0.1);
        }
        stoptime = true;
    }

    while(gamepad2.left_stick_y < 0.1){
        int currentSlidePosition = slide.getCurrentPosition();
        if (currentSlidePosition <= slideMax)
        {
            slide.setPower(0);
        }
        else {
            lowerSlide(0.1);
        }
        stoptime = true;
    }

    if(stoptime = true) {
        stoptime = false;
        sleep(100);
    }

    public void lowerSlide(double power) {
        slide.setPower(-power);
    }

    public void raiseSlide(double power) {
        slide.setPower(power);
    }

    @Override
    public void driveForward(double inches, double power) {
     int ticks = (int)(inches * 60);

     rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

     rightBack.setTargetPosition(ticks);

     leftFront.setPower(-power);
     rightFront.setPower(power);
     leftBack.setPower(-power);
     rightBack.setPower(power);

     rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

     while(rightBack.isBusy()) {
            //Nothing
     }

     leftFront.setPower(0);
     rightFront.setPower(0);
     leftBack.setPower(0);
     rightBack.setPower(0);

    }

    @Override
    public void driveBack(double inches, double power) {
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setTargetPosition(-ticks);

        leftFront.setPower(power);
        rightFront.setPower(-power);
        leftBack.setPower(power);
        rightBack.setPower(-power);

        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {

        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

    @Override
    public void turnRight(double inches, double power) {
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);

        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {

        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);



    }

    @Override
    public void turnLeft(double inches, double power) {
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        rightBack.setPower(-power);

        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {

        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }


    @Override
    public void moveRight(double inches, double power) {
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(-power);
        rightBack.setPower(-power);

        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {

        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

    @Override
    public void moveLeft(double inches, double power) {
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(power);
        rightBack.setPower(power);

        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {

        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

    @Override
    public void raiseClaw(double power, int TargetPosition, long timeMs) {
        int currentSlidePosition = slide.getCurrentPosition();
        if ((currentSlidePosition + (TargetPosition - currentSlidePosition) > slideMax))
        {
            slide.setPower(0);
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
        if ((currentSlidePosition + (TargetPosition - currentSlidePosition) < 0))
        {
            slide.setPower(0);
        }
        else
            slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            slide.setTargetPosition(TargetPosition);

            slide.setPower(-power);

            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

}