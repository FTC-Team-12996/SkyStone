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

        while (opModeIsActive()) {
            clawServo.setPosition(0.1); // Resets the servo
            sleep(100);

            while (sensorDistance.getDistance(DistanceUnit.CM) > 7) {
                sensorDistance.getDistance(DistanceUnit.CM);
            }


            telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
            telemetry.update();

            // Drives forwards while the stones are out of sight
            while (Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM)) || sensorDistance.getDistance(DistanceUnit.CM) > 10) {
                driveBase.driveForward(-0.5);
                sensorDistance.getDistance(DistanceUnit.CM);
                telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
                telemetry.update();
            }

            driveBase.stopMotors();

            sleep(500);

            // If the skystone is detected, it is picked up and the loop ends
            // If it's not, the robot moves to the next stone and tries again
            for (int x = 0; x < 2; x++) {
                driveBase.driveForward(2, 0.5);
                for (int i = 0; i < 100; i++) { // Takes the average of 100 measurements from the color sensor
                    alphaAvrg += colorsensor.red();
                    sleep(5);
                }

                telemetry.addData("Alpha: ", alphaAvrg / 100);
                telemetry.update();
                sleep(250);

                if (alphaAvrg / 100 < 250) {
                    driveBase.driveForward(1, 0.5);
                    sleep(100);
                    clawServo.setPosition(1);
                    skystoneIsDetected = true;
                    telemetry.addData("in the if: ", x);
                    telemetry.update();
                    break;
                } else {
                    driveBase.driveBackwards(2, 0.5);
                    driveBase.moveRight(3, 1);
                    telemetry.addData("in the else: ", x);
                    telemetry.update();
                }
                alphaAvrg = 0;
                sleep(1000);
            }

            telemetry.addData("out: ", null);
            telemetry.update();

            sleep(500);

            if (!skystoneIsDetected) { // Grabs the last stone if the others were not identified as the skystone
                telemetry.addData("Third stone is skystone", null);
                telemetry.update();
                driveBase.driveForward(5, 0.5);
                clawServo.setPosition(1);
                skystoneIsDetected = true;
                driveBase.moveRight(5, 1);

            }

            driveBase.driveBackwards(25, 1); // Backs away from the skystone

            sleep(100); // Pause for gear safety

            driveBase.moveLeft(300, 1); // Moves over the midline

            clawServo.setPosition(0.1); // Drops the skystone

            driveBase.moveRight(100, 1); // Moves back onto the midline

            sleep(100); // Pause for gear safety

            driveBase.turnLeft(3, 1); // Turns to maximize size of the robot over midline

            clawServo.setPosition(0.5); // Lowers the claw to maximize size
        }
    }
}
