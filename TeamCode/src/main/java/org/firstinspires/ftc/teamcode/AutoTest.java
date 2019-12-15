// Autonomous code developed by Andy Greer during the 2019 season

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

//@Autonomous

public class AutoTest extends LinearOpMode {
    private ColorSensor colorsensor;
    private DistanceSensor sensorDistance;
    private Servo clawServo;
    private DcMotor slide;
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
        slide = hardwareMap.get(DcMotor.class, "slide");

        waitForStart();

        while (opModeIsActive()) {
            slide.setTargetPosition(4500);
                slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide.setPower(1);

                while (slide.isBusy() && opModeIsActive()) {

                }
                slide.setPower(0);

            slide.setTargetPosition(-250);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slide.setPower(-1);

            while (slide.isBusy() && opModeIsActive()) {

            }
            slide.setPower(0);

            break;
        }
    }
}

