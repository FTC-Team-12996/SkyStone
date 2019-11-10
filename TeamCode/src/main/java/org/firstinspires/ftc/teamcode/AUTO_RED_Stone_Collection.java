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

        waitForStart();

        while(opModeIsActive()) {
            clawServo.setPosition(0);

            while(Double.isNaN(sensorDistance.getDistance(DistanceUnit.CM))) {
                driveBase.driveForward(0.5);
            }

            while(sensorDistance.getDistance(DistanceUnit.CM) > 1) {
                driveBase.driveForward(0.1);
            }

            driveBase.stopMotors();

            while(!skystoneIsDetected) {
                for (int x = 0; x < 6; x++) {
                    for (int i = 0; i < 100; i++) {
                        alphaAvrg += colorsensor.alpha();
                        sleep(10);
                    }

                    if (alphaAvrg < 400) {
                        clawServo.setPosition(1);
                        skystoneIsDetected = true;
                    } else {
                        driveBase.moveLeft(5, 0.3);
                    }
                    alphaAvrg = 0;
                }
            }
        }

        driveBase.driveBackwards(10, 1);

        sleep(100);

        driveBase.moveRight(60, 1);

        clawServo.setPosition(0);

        driveBase.moveLeft(20, 1);

        sleep(100);

        driveBase.turnRight(3, 1);

        clawServo.setPosition(0.5);
    }
}
