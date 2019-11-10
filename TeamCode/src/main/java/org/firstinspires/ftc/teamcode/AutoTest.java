package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous

// One rotation of the mecanum wheels is 12 inches
// One rotation of the mecanum wheels is 1440 ticks

// COLOR SENSOR
// 

public class AutoTest extends LinearOpMode {
    ColorSensor colorsensor;
    DistanceSensor sensorDistance;
    int alphaAvrg = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBase protoBot = new DriveBase(hardwareMap);
        colorsensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorSensor");
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        waitForStart();

        while(opModeIsActive()) {

//            colorsensor.enableLed(false);
//            sleep(1000);
//            colorsensor.enableLed(true);
//            sleep(100);
//
////             send the info back to driver station using telemetry function.
//            telemetry.addData("Alpha", colorsensor.alpha());
//            telemetry.addData("Red  ", colorsensor.red());
//            telemetry.addData("Green", colorsensor.green());
//            telemetry.addData("Blue ", colorsensor.blue());

//            relativeLayout.post(new Runnable() {
//                public void run() {
//                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
//                }
//            });

            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.INCH)));
            telemetry.update();
            sleep(500);



            for(int i = 0; i < 100; i++) {
                alphaAvrg +=  colorsensor.alpha();
                sleep(10);
            }
////            telemetry.addData("Alpha", (alphaAvrg / 100));
//            alphaAvrg = 0;
//
//            telemetry.update();
//
//            if(colorsensor.alpha() < 400) {
//                telemetry.addData("Skystone", "");
//                telemetry.update();
//            }
//            else{
//                telemetry.addData("Color", "Else");
//                telemetry.update();
//            }

            //stop();
        }
    }
}

