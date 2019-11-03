package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class N_Autonomous extends LinearOpMode {

    boolean stoptime = false;

    public void runOpMode() throws InterruptedException {

        waitForStart();

        while(opModeIsActive()) {

            // Create an AutonomousFunctions object to fix the rest of the errors
//
//            while (gamepad2.left_stick_y > 0.1) {
//                int currentSlidePosition = slide.getCurrentPosition();
//                if (currentSlidePosition >= slideMax) {
//                    slide.setPower(0);
//                } else {
//                    raiseSlide(0.1);
//                }
//                stoptime = true;
//            }
//
//            while (gamepad2.left_stick_y < 0.1) {
//                int currentSlidePosition = slide.getCurrentPosition();
//                if (currentSlidePosition <= slideMax) {
//                    slide.setPower(0);
//                } else {
//                    lowerSlide(0.1);
//                }
//                stoptime = true;
//            }

            if (stoptime) {
                stoptime = false;
                sleep(100);
            }
        }
    }
}
