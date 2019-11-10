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

public class AUTO_BLUE_Stone_Collection extends LinearOpMode {
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

        while(opModeIsActive()) {
            clawServo.setPosition(0); // Resets the servo

            while(Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM))) { // Drives until the stones are in view of the distance sensor
                driveBase.driveForward(0.5);
            }

            while(sensorDistance.getDistance(DistanceUnit.CM) > 1) { // Drives slowly up next to the first stone
                driveBase.driveForward(0.1);
            }

            driveBase.stopMotors();


            // If the skystone is detected, it is picked up and the loop ends
            // If it's not, the robot moves to the next stone and tries again
            for (int x = 0; x < 5; x++) {
                for (int i = 0; i < 100; i++) { // Takes the average of 100 measurements from the color sensor
                    alphaAvrg += colorsensor.alpha();
                    sleep(10);
                }

                if (alphaAvrg < 400) {
                    clawServo.setPosition(1);
                    skystoneIsDetected = true;
                    break;
                } else {
                    driveBase.moveRight(5, 0.3);
                }
                alphaAvrg = 0;
            }

            if(!skystoneIsDetected) { // Grabs the last stone if the others were not identified as the skystone
                clawServo.setPosition(1);
                skystoneIsDetected = true;
                driveBase.moveRight(5, 0.3);

            }
        }

        driveBase.driveBackwards(10, 1); // Backs away from the skystone

        sleep(100); // Pause for gear safety

        driveBase.moveLeft(60, 1); // Moves over the midline

        clawServo.setPosition(0); // Drops the skystone

        driveBase.moveRight(20, 1); // Moves back onto the midline

        sleep(100); // Pause for gear safety

        driveBase.turnLeft(3, 1); // Turns to maximize size of the robot over midline

        clawServo.setPosition(0.5); // Lowers the claw to maximize size
    }
}
