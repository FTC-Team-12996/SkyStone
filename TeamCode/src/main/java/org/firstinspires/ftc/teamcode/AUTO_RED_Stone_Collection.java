// Autonomous code developed by Andy Greer during the 2019 season

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous

public class AUTO_RED_Stone_Collection extends LinearOpMode {
    private ColorSensor colorsensor;
    private DistanceSensor sensorDistance;
    private Servo clawServo;
    private boolean skystoneIsDetected = false;
    private int alphaAvrg;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    public void runOpMode() {

        DriveBase driveBase = new DriveBase(hardwareMap);
        colorsensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");

        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        //rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            clawServo.setPosition(0); // Resets the servo
            sleep(100);
            sensorDistance.getDistance(DistanceUnit.CM);
            telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
            telemetry.update();

            while (Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM)) || sensorDistance.getDistance(DistanceUnit.CM) > 7) { // Drives forwards while the stones are out of sight
                driveBase.driveForward(-0.5);
                sensorDistance.getDistance(DistanceUnit.CM);
                telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
                telemetry.update();
            }
            driveBase.stopMotors();

            // Drives up to the first stone
//                driveBase.driveForward(-0.1);
//                sensorDistance.getDistance(DistanceUnit.CM);
//                telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
//                telemetry.update();

            driveBase.stopMotors();

            // If the skystone is detected, it is picked up and the loop ends
            // If it's not, the robot moves to the next stone and tries again
            for (int x = 0; x < 2; x++) {
                for (int i = 0; i < 100; i++) {
                    alphaAvrg += colorsensor.alpha();
                    sleep(5);
                }
                if (alphaAvrg / 100 < 400) {
                    clawServo.setPosition(1);
                    skystoneIsDetected = true;
                    break;
                } else {
                    driveBase.moveLeft(5, 1);
                    driveBase.stopMotors();
                }
                alphaAvrg = 0;
            }

            if(!skystoneIsDetected) { // Grabs the leftover stone if none have been grabbed
                clawServo.setPosition(1);
                skystoneIsDetected = true;
                driveBase.moveLeft(5, 1);
                driveBase.stopMotors();
            }

            driveBase.driveBackwards(10, 1); // Backs away from the stones

            sleep(100); // Pauses for gear safety

            driveBase.moveRight(60, 1); // Moves across the midline

            clawServo.setPosition(0); // Drops the skystone

            driveBase.moveLeft(20, 1); // Moves back onto the midline

            sleep(100); // Pauses for gear safety

            driveBase.turnRight(3, 1); // Turns to maximize size of the robot over midline

            clawServo.setPosition(0.5); // Drops the servo to maximize size of the robot
        }
    }
}
