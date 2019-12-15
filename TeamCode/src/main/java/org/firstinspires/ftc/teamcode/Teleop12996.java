package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Teleop12996 extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    boolean done = true;
    boolean stoptime = false;
    private Servo clawServo;
    private DcMotor slide;

    private boolean pauseTime = false;


    @Override
    public void runOpMode() {
        DriveBase protoBot = new DriveBase(hardwareMap);
        //imu = hardwareMap.get(Gyroscope.class, "imu");
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        slide = hardwareMap.get(DcMotor.class, "slide");

        clawServo = hardwareMap.get(Servo.class, "clawServo");

//        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        clawServo.setPosition(0.1);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            try {

                if (gamepad1.left_stick_y > 0.1) { // Forward with the y-values of the left joystick
                    protoBot.driveForward(gamepad1.left_stick_y);
                    stoptime = true;
                } else if (gamepad1.left_stick_y < -0.1) { // Backwards with the y-values of the left joystick
                    protoBot.driveBackwards(gamepad1.left_stick_y);
                    stoptime = true;
                } else if (gamepad1.left_stick_x > 0.1) { // turn left
                    protoBot.turn(-gamepad1.left_stick_x);
                    stoptime = true;
                } else if (gamepad1.left_stick_x < -0.1) { // turn right
                    protoBot.turn(-gamepad1.left_stick_x);
                    stoptime = true;
                } else if (gamepad1.right_stick_x > 0.1) { // move right
                    protoBot.right(-gamepad1.right_stick_x);
                    stoptime = true;
                } else if (gamepad1.right_stick_x < -0.1) { // move left
                    protoBot.left(gamepad1.right_stick_x);
                    stoptime = true;
                }

                if (gamepad1.right_stick_x < 0.1 && gamepad1.right_stick_x > -0.1 && gamepad1.left_stick_x < 0.1 &&
                        gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_y < 0.1 && gamepad1.left_stick_y > -0.1 &&
                        gamepad2.right_stick_y < 0.1 && gamepad2.right_stick_y > -0.1) { // stops the wheels
                    protoBot.stopMotors();
                }

                if (gamepad2.left_stick_y > 0.1 && slide.getCurrentPosition() >= 0) {
                    slide.setPower(-gamepad2.left_stick_y);
                    telemetry.update();
                } else if (gamepad2.left_stick_y < -0.1 && slide.getCurrentPosition() <= 14400) {
                    slide.setPower(-gamepad2.left_stick_y);
                    telemetry.update();
                } else if (gamepad2.dpad_down) {
                    slide.setPower(-0.4);
                    telemetry.update();
                } else if (gamepad2.dpad_up) {
                    slide.setPower(0.4);
                    telemetry.update();
                } else if ((slide.getCurrentPosition() > 14400 || slide.getCurrentPosition() < 0) || (gamepad2.left_stick_y > -0.1
                        && gamepad2.left_stick_y < 0.1) && !gamepad2.dpad_up && !gamepad2.dpad_down) {
                    slide.setPower(0);
                }

                if (gamepad2.y) {
                    clawServo.setPosition(0.1);
                } else if (gamepad2.a) {
                    clawServo.setPosition(1);
                }

                telemetry.addData("Ticks: ", slide.getCurrentPosition());
                telemetry.addData("Gamepad2 left stick y-value: ", gamepad2.left_stick_y);
                telemetry.addData("Slide power: ", slide.getPower());
                telemetry.update();
            }

            catch (Exception e) {
                telemetry.addData("The code is broken restart the program.", "Error: " + e);
                telemetry.addData("AAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" +
                        "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" +
                        "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH", "Error: " + e);
                telemetry.update();
            }
        }

    }

}


