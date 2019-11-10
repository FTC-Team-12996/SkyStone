// Autonomous code developed by Andy Greer during the 2019 season

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

    public void runOpMode() {

        DriveBase driveBase = new DriveBase(hardwareMap);
        colorsensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        waitForStart();

        while (opModeIsActive()) {
            clawServo.setPosition(0); // Resets the servo

            while (Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM))) { // Drives forwards while the stones are out of sight
                driveBase.driveForward(0.5);
            }

            while (sensorDistance.getDistance(DistanceUnit.CM) > 1) { // Drives up to the first stone
                driveBase.driveForward(0.1);
            }

            driveBase.stopMotors();

            // If the skystone is detected, it is picked up and the loop ends
            // If it's not, the robot moves to the next stone and tries again
            for (int x = 0; x < 5; x++) {
                for (int i = 0; i < 100; i++) {
                    alphaAvrg += colorsensor.alpha();
                    sleep(10);
                }
                if (alphaAvrg < 400) {
                    clawServo.setPosition(1);
                    skystoneIsDetected = true;
                    break;
                } else {
                    driveBase.moveLeft(5, 0.3);
                }
                alphaAvrg = 0;
            }

            if(!skystoneIsDetected) { // Grabs the leftover stone if none have been grabbed
                clawServo.setPosition(1);
                skystoneIsDetected = true;
                driveBase.moveLeft(5, 0.3);
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
