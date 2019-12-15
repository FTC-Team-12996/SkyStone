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

public class AUTO_BLUE_Stone__Collection extends LinearOpMode {
    private ColorSensor colorsensor;
    private DistanceSensor sensorDistance;
    private Servo clawServo;
    private boolean skystoneIsDetected = false;
    private int alphaAvrg;
    private int moveDistance = 0;

    public void runOpMode() {

        DriveBase driveBase = new DriveBase(hardwareMap);
        colorsensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        waitForStart();

        while (opModeIsActive()) {
            try {

                clawServo.setPosition(0.1); // Resets the servo
                colorsensor.enableLed(true); // Turns on the color sensor

                while (sensorDistance.getDistance(DistanceUnit.CM) > 7) {
                    sensorDistance.getDistance(DistanceUnit.CM);
                }


                telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
                telemetry.update();

                // Drives forwards while the stones are out of sight
                while (Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM)) || sensorDistance.getDistance(DistanceUnit.CM) > 8) {
                    driveBase.driveForward(-0.5);
                    sensorDistance.getDistance(DistanceUnit.CM);
                    telemetry.addData("Distance: ", sensorDistance.getDistance(DistanceUnit.CM));
                    telemetry.update();
                }

                driveBase.stopMotors();

                // If the skystone is detected, it is picked up and the loop ends
                // If it's not, the robot moves to the next stone and tries again
                for (int x = 0; x < 2; x++) {
                    //driveBase.driveForward(2, 0.5);
                    //driveBase.turnLeft(1, 0.5);
                    for (int i = 0; i < 100; i++) { // Takes the average of 100 measurements from the color sensor
                        alphaAvrg += colorsensor.red();
                        sleep(5);
                    }

                    telemetry.addData("Red: ", alphaAvrg / 100);
                    telemetry.update();

                    if (alphaAvrg / 100 < 150) {
                        driveBase.driveForward(15, 0.5);
                        clawServo.setPosition(1);
                        sleep(500);
                        skystoneIsDetected = true;
                        moveDistance = 120;
                        telemetry.addData("moveDistance", moveDistance);
                        telemetry.update();
                        break;
                    } else {
                        driveBase.driveBackwards(1, 0.5);
                        driveBase.moveLeft(17, 0.5);
                        driveBase.driveForward(2, 0.5);
                    }
                    alphaAvrg = 0;
                }

                colorsensor.enableLed(false); // Turns off the color sensor after use

                if (!skystoneIsDetected) { // Grabs the last stone if the others were not identified as the skystone
                    driveBase.driveForward(15, 0.5);
                    clawServo.setPosition(1);
                    sleep(500);
                    moveDistance = 145;
                    telemetry.addData("moveDistance", moveDistance);
                    telemetry.update();
                    skystoneIsDetected = true;

                }

                driveBase.driveBackwards(45, 1); // Backs away from the skystone

                sleep(100); // Pause for gear safety

                driveBase.moveRight(moveDistance, 1); // Moves over the midline

                clawServo.setPosition(0.1); // Drops the skystone

                driveBase.moveLeft(moveDistance - 90, 1); // Moves back onto the midline

                clawServo.setPosition(0.5); // Lowers the claw to maximize size

                break;

            }
            catch(Exception e) {
                telemetry.addData("RESTART PROGRAM, ERROR: ", e);
                telemetry.update();
            }
        }
    }
}