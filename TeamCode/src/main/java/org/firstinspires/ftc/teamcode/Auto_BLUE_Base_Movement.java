// Autonomous code developed by Noah Cohen and Andy Greer during the 2019 season

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class Auto_BLUE_Base_Movement extends LinearOpMode {

    private DcMotor slide;
    private Servo clawServo;

    public void runOpMode() {

        DriveBase driveBase = new DriveBase(hardwareMap);
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        DcMotor slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            try {

                clawServo.setPosition(0.1);

                slide.setTargetPosition(4500);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setPower(1);

                while (slide.isBusy() && opModeIsActive()) {

                }
                slide.setPower(0);

                driveBase.moveRight(25, 0.5);

                driveBase.driveForward(70, 0.75);

                slide.setTargetPosition(-250);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setPower(-1);

                while (slide.isBusy() && opModeIsActive()) {

                }
                slide.setPower(0);

                driveBase.driveBackwards(80, 0.75);
                driveBase.turnLeft(5, 0.5);

                slide.setTargetPosition(4500);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setPower(1);

                while (slide.isBusy() && opModeIsActive()) {

                }
                slide.setPower(0);

                driveBase.moveLeft(80, 1);
                driveBase.driveForward(15, 1);
                driveBase.moveRight(50, 1);

                slide.setTargetPosition(-250);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setPower(-1);

                while (slide.isBusy() && opModeIsActive()) {

                }
                slide.setPower(0);

                driveBase.turnRight(5, 1);
                driveBase.moveLeft(85, 1);
                driveBase.driveBackwards(30, 1);

                break;
            }
            catch(Exception e) {
                telemetry.addData("RESTART PROGRAM, ERROR: ", e);
                telemetry.update();
            }

        }
    }
}
