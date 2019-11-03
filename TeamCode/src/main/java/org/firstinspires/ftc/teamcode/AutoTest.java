package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous

// One rotation of the mecanum wheels is 12 inches
// One rotation of the mecanum wheels is 1440 ticks

// COLOR SENSOR
// 

public class AutoTest extends LinearOpMode {
    ColorSensor colorsensor;

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBase protoBot = new DriveBase(hardwareMap);
        colorsensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        waitForStart();

        while(opModeIsActive()) {

            colorsensor.enableLed(false);
            sleep(1000);
            colorsensor.enableLed(true);
            sleep(100);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Alpha", colorsensor.alpha());
            telemetry.addData("Red  ", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue ", colorsensor.blue());

//            relativeLayout.post(new Runnable() {
//                public void run() {
//                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
//                }
//            });

            telemetry.update();

            if(colorsensor.alpha() > 200 && colorsensor.alpha() < 250) {
                telemetry.addData("Color", "Black");
                telemetry.update();
            }
            else{
                telemetry.addData("Color", "Else");
                telemetry.update();
            }

            //stop();
        }
    }
}

