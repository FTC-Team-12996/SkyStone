package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp
public class Linear_Slide_Test extends LinearOpMode {
private DcMotor slide;

    @Override
    public void runOpMode() {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            while(gamepad2.a) {
                slide.setPower(-1);
                telemetry.addData("Ticks: ", slide.getCurrentPosition());
                telemetry.update();
            }
            while(gamepad2.y) {
                slide.setPower(1);
                telemetry.addData("Ticks: ", slide.getCurrentPosition());
                telemetry.update();
            }
            slide.setPower(0);
            telemetry.update();
        }
    }

}
