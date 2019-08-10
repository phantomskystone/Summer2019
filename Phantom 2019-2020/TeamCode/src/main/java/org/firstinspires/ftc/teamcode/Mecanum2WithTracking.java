package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

@TeleOp(name = "MecanumWithTracking")
public class Mecanum2WithTracking extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();

    double frontLeft;
    double frontRight;
    double backLeft;
    double backRight;
    double side;
    double oldAngle;

    boolean pressed = false;

    double power = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        robot.imu.initialize(robot.parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();
        Tracker tracking = new Tracker(robot.imu);
        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart();
        while (!isStopRequested()) {
            double nanos = tracking.getNanos();//start time
            if (gamepad1.right_bumper && !pressed && power < 1) {
                power += 0.25;
                pressed = true;
            }
            if (gamepad1.left_bumper && !pressed && power > 0.25) {
                power -= 0.25;
                pressed = true;
            }

            if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
                pressed = false;
            }

            telemetry.addData("Power:", power);


            side = (gamepad1.left_stick_x + gamepad1.right_stick_x) / -2;
            frontRight = -gamepad1.left_stick_y + side;
            backRight = -gamepad1.left_stick_y - side;
            frontLeft = -gamepad1.right_stick_y - side;
            backLeft = -gamepad1.right_stick_y + side;
            if (gamepad1.dpad_left) {

                frontLeft = -1 - gamepad1.right_trigger;
                frontRight = 1 * (1 - gamepad1.right_trigger);
                backLeft = 1 * (1 - gamepad1.right_trigger);
                backRight = -1 - gamepad1.right_trigger;

            } else if (gamepad1.dpad_right) {

                frontLeft = 1 * (1 - gamepad1.right_trigger);
                frontRight = -1 - gamepad1.right_trigger;
                backLeft = -1 - gamepad1.right_trigger;
                backRight = 1 * (1 - gamepad1.right_trigger);
            }
            if (!gamepad1.x) {
                robot.frontLeft.setPower(-frontLeft * power);
                robot.backLeft.setPower(-backLeft * power);
                robot.frontRight.setPower(-frontRight * power);
                robot.backRight.setPower(-backRight * power);
            } else {
                rotate(170,1);
            }
           // double[] returned = tracking.Track(tracking.getNanoDiffs(nanos));//get the end time and diff it with the start time, then get the relative position.
           //telemetry.addData("XRelative: ",returned[0]);
           //telemetry.addData("YRelative: ",returned[1]);
           //telemetry.addData("ZRelative: ",returned[2]);
            telemetry.addData("Test: ",tracking.Track(tracking.getNanoDiffs(nanos)));
            telemetry.update();
        }
    }
    private void resetAngle()
    {
        robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        robot.globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - robot.lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        robot.globalAngle += deltaAngle;

        robot.lastAngles = angles;

        return robot.globalAngle;
    }
    private void rotate(int degrees, double rPower)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = rPower;
            rightPower = -rPower;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -rPower;
            rightPower = rPower;
        }
        else return;

        // set power to rotate.
        robot.backLeft.setPower(leftPower);
        robot.frontLeft.setPower(leftPower);
        robot.backRight.setPower(rightPower);
        robot.frontRight.setPower(rightPower);
        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {telemetry.addData("heading",robot.globalAngle); telemetry.update();}

            while (opModeIsActive() && getAngle() > degrees) {telemetry.addData("heading",robot.globalAngle); telemetry.update();}
        }
        else    // left turn.
            while (opModeIsActive() && getAngle() < degrees) {telemetry.addData("heading",robot.globalAngle); telemetry.update();}

        // turn the motors off.
        robot.backLeft.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.frontRight.setPower(0);
        // wait for rotation to stop.
            sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }

}
