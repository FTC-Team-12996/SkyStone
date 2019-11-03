package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class testRobot1 extends LinearOpMode {
    //private Gyroscope imu;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    boolean done = true;
    boolean stoptime = false;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    private Servo clawServo;
    private boolean pauseTime = false;
    private DcMotor slide;

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
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            while(gamepad1.left_stick_y > 0.1) { // Forward with the y-values of the left joystick
                protoBot.driveForward(-gamepad1.left_stick_y);
                stoptime = true;
            }

            while(gamepad1.left_stick_y < -0.1) { // Backwards with the y-values of the left joystick
                protoBot.driveBackwards(-gamepad1.left_stick_y);
                stoptime = true;
            }

            while(gamepad1.left_stick_x > 0.1) { // turn left
                protoBot.turn(gamepad1.left_stick_x);
                stoptime = true;

                telemetry.addData("Right Stick X value:", gamepad1.right_stick_x);
                telemetry.update();
            }

            while(gamepad1.left_stick_x < -0.1) { // turn right
                protoBot.turn(gamepad1.left_stick_x);
                stoptime = true;

                telemetry.addData("Right Stick X value:", gamepad1.right_stick_x);
                telemetry.update();
            }
            while(gamepad1.right_stick_x > 0.1) { // move right
                protoBot.right(-gamepad1.right_stick_x);
                stoptime = true;
            }
            while (gamepad1.right_stick_x < -0.1) { // move left
                protoBot.left(gamepad1.right_stick_x);
                stoptime = true;
            }
            while (gamepad2.left_stick_y > 0.1) {
                int currentSlidePosition = slide.getCurrentPosition();
                if (currentSlidePosition >= slideMax) {
                    slide.setPower(0);
                } else {
                    raiseSlide(0.1);
                }
                stoptime = true;
            }

            while (gamepad2.left_stick_y < 0.1) {
                int currentSlidePosition = slide.getCurrentPosition();
                if (currentSlidePosition <= slideMax) {
                    slide.setPower(0);
                } else {
                    lowerSlide(0.1);
                }
                stoptime = true;
            }


            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            if(stoptime) {
                sleep(100);
                stoptime = false;
            }

            if(gamepad1.a) {
                clawServo.setPosition(0);
                telemetry.addData("a is pressed", null);
            }
            else if(gamepad1.b) {
                clawServo.setPosition(0.5);
            }
            else if(gamepad1.y) {
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


