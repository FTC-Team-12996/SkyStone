package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class testRobot1 extends LinearOpMode {
    //private Gyroscope imu;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    boolean done = true;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    //private Servo servoTest;


    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");



//            if(done) {
//
//                leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks
//
//                leftFront.setTargetPosition(1440);
//
//                leftFront.setPower(0.2);
//
//                leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }
//            while(leftFront.isBusy()) {
//                // wait
//            }
//
//            leftFront.setPower(0);
//
//            done = false;

            while(gamepad1.left_stick_y > 0.1) { // Forward with the y-values of the left joystick
                leftFront.setPower(gamepad1.left_stick_y);
                leftBack.setPower(gamepad1.left_stick_y);
                rightFront.setPower(-gamepad1.left_stick_y);
                rightBack.setPower(-gamepad1.left_stick_y);
                telemetry.addData("Left Stick Y value:", gamepad1.left_stick_y);
                telemetry.update();
            }

            while(gamepad1.left_stick_y < -0.1) { // Backwards with the y-values of the left joystick
                leftFront.setPower(gamepad1.left_stick_y);
                leftBack.setPower(gamepad1.left_stick_y);
                rightFront.setPower(-gamepad1.left_stick_y);
                rightBack.setPower(-gamepad1.left_stick_y);
                telemetry.addData("Left Stick Y value:", gamepad1.left_stick_y);
                telemetry.update();
            }

            while(gamepad1.left_stick_x > 0.1) { // Forward with the y-values of the left joystick
                leftFront.setPower(gamepad1.left_stick_x);
                leftBack.setPower(gamepad1.left_stick_x);
                rightFront.setPower(gamepad1.left_stick_x);
                rightBack.setPower(gamepad1.left_stick_x);
                telemetry.addData("Right Stick X value:", gamepad1.right_stick_x);
                telemetry.update();
            }

            while(gamepad1.left_stick_x < -0.1) { // Forward with the y-values of the left joystick
                leftFront.setPower(gamepad1.left_stick_x);
                leftBack.setPower(gamepad1.left_stick_x);
                rightFront.setPower(gamepad1.left_stick_x);
                rightBack.setPower(gamepad1.left_stick_x);
                telemetry.addData("Right Stick X value:", gamepad1.right_stick_x);
                telemetry.update();
            }
            while(gamepad1.right_stick_x > 0.1) {
                leftFront.setPower(gamepad1.right_stick_x);
                leftBack.setPower(-gamepad1.right_stick_x);
                rightFront.setPower(gamepad1.right_stick_x);
                rightBack.setPower(-gamepad1.right_stick_x);
            }
            while (gamepad1.right_stick_x < -0.1) {
                leftFront.setPower(gamepad1.right_stick_x);
                leftBack.setPower(-gamepad1.right_stick_x);
                rightFront.setPower(gamepad1.right_stick_x);
                rightBack.setPower(-gamepad1.right_stick_x);
            }

            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);



            telemetry.update();

        }
    }
}


