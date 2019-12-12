package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp
public class SlideMax_test extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    boolean done = true;
    boolean stoptime = false;
    private Servo clawServo;
    private DcMotor slide;

    private boolean pauseTime = false;
    final int slideMax = 2280;


    @Override
    public void runOpMode() {
        DriveBase protoBot = new DriveBase(hardwareMap);
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            while(gamepad2.dpad_up) {
                while (rightBack.getCurrentPosition() < slideMax) {
                    rightBack.setPower(0.5);
                }
            }
            while(gamepad2.dpad_up) {
                while (rightBack.getCurrentPosition() >= 0) {
                    rightBack.setPower(-0.5);
                }
            }


            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            if (stoptime) {
                sleep(100);
                stoptime = false;
            }

            if (gamepad1.a) {
                clawServo.setPosition(0);
                telemetry.addData("a is pressed", null);
            } else if (gamepad1.b) {
                clawServo.setPosition(0.5);
            } else if (gamepad1.y) {
                clawServo.setPosition(1);
            }


            telemetry.update();

        }

    }

    public void lowerSlide(double power) {
        slide.setPower(-power);
    }

    public void raiseSlide(double power) {
        slide.setPower(power);
    }

}
