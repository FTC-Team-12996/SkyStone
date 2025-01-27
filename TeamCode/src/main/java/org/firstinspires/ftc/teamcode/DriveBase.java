package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveBase {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor[] motorController = new DcMotor[4];


    public DriveBase(HardwareMap hardwareMap) {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        motorController[0] = leftFront;

        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        motorController[1] = rightFront;

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        motorController[2] = leftBack;

        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorController[3] = rightBack;

        motorController[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorController[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorController[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorController[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void stopMotors() {
        for(int i = 0; i < motorController.length; i++) {
            motorController[i].setPower(0);
        }
    }

    // Driver controlled functions
    public void driveForward(double speed) {
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        rightFront.setPower(-speed);
        rightBack.setPower(-speed);
    }
    public void driveBackwards(double speed) {
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        rightFront.setPower(-speed);
        rightBack.setPower(-speed);
    }
    public void turn(double speed) {
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        rightFront.setPower(speed);
        rightBack.setPower(speed);
    }
    public void right(double speed) {
        leftFront.setPower(speed);
        leftBack.setPower(-speed);
        rightFront.setPower(speed);
        rightBack.setPower(-speed);
    }
    public void left(double speed) {
        leftFront.setPower(-speed);
        leftBack.setPower(speed);
        rightFront.setPower(-speed);
        rightBack.setPower(speed);
    }

    // Autonomous functions
    public void driveForward(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // left is positive while right is negative
        // rightBack has an encoder
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(-speed + 0.05);
        rightFront.setPower(speed);
        leftBack.setPower(-speed + 0.05);
        rightBack.setPower(speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void driveBackwards(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // left is positive while right is negative
        // rightBack has an encoder
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(-ticks);

        rightFront.setPower(-speed);
        leftBack.setPower(speed);
        rightBack.setPower(-speed);
        leftFront.setPower(speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void turnRight(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // left is positive while right is negative
        // rightBack has an encoder
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(-ticks);

        leftFront.setPower(-speed);
        rightFront.setPower(-speed);
        leftBack.setPower(-speed);
        rightBack.setPower(-speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void turnLeft(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // left is positive while right is negative
        // rightBack has an encoder
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void moveRight(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(-ticks);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(-speed);
        rightBack.setPower(-speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }


    public void moveLeft(double inches, double speed) {
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int ticks = (int)(inches * 60);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Torquenado has 1440 ticks

        rightBack.setTargetPosition(ticks);

        leftFront.setPower(-speed);
        rightFront.setPower(-speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);


        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(rightBack.isBusy()) {
            // wait
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


}

