// Autonomous code developed by Andy Greer during the 2019 season

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutoTest extends LinearOpMode {
    private ColorSensor colorsensor;
    private DistanceSensor sensorDistance;
    private Servo clawServo;
    private boolean skystoneIsDetected = false;
    private int alphaAvrg;
    private int redAvrg;
    private int blueAvrg;
    private int greenAvrg;
    private int rgbAvrg;

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
            for (int i = 0; i < 100; i++) { // Takes the average of 100 measurements from the color sensor
                alphaAvrg += colorsensor.alpha();
                redAvrg += colorsensor.red();
                blueAvrg += colorsensor.blue();
                greenAvrg += colorsensor.green();
                rgbAvrg += colorsensor.argb();
                sleep(5);
            }
            telemetry.addData("Alpha: ", alphaAvrg / 100);
            telemetry.addData("Red: ", redAvrg / 100);
            telemetry.addData("Blue: ", blueAvrg / 100);
            telemetry.addData("Green: ", greenAvrg / 100);
            telemetry.addData("RGB: ", rgbAvrg / 100);
            telemetry.update();

            alphaAvrg = 0;
            redAvrg = 0;
            blueAvrg = 0;
            greenAvrg = 0;
            rgbAvrg = 0;
        }
    }
}

