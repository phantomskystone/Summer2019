package org.firstinspires.ftc.teamcode.raameshcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import org.firstinspires.ftc.teamcode.MecanumConfig;
public class MethodsWithoutVuforia extends LinearOpMode {



    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */


    /**
     * This is the webcam we are to use. As with other hardware devices such as motors and
     * servos, this device is identified using the robot configuration tool in the FTC application.
     */

    public void runOpMode() {}
        /*
         * Retrieve the camera we are to use.
         */


        public double robotSpeedinInchPerSec = 41;

        public boolean aligned = false;
    public double trackingDistance = 0;




    MecanumConfig robot = new MecanumConfig();

    public ElapsedTime runtime1 = new ElapsedTime();

   /* public void ArtficialEncoders(double powerPercent, double Distance, double strafeAngle) {

        robot.globalAngle = 0;

        mecanumTimeDrive(strafeAngle,(Distance/robotSpeedinInchPerSec*(powerPercent/100)),powerPercent);

    } */
    /* public void mecanumDrive(double angle, double maxAllowedPower) {

        angle = -angle - 45;

        runtime1.reset();

        telemetry.addData("Angle X", getAngle());
        telemetry.addData("Correction", checkDirection());
        telemetry.addData("lastangles", robot.lastAngles.firstAngle);
        telemetry.update();
        robot.frontRight.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
        robot.frontLeft.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
        robot.backRight.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
        robot.backLeft.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());

    } */

   /* public void mecanumTimeDrive(double angle, double timeS, double maxAllowedPower) {

        angle = -angle - 45;

        runtime1.reset();
        while (!isStopRequested() && runtime1.seconds() < timeS) {
            telemetry.addData("Angle X", getAngle());
            telemetry.addData("Correction", checkDirection());
            telemetry.addData("lastangles", robot.lastAngles.firstAngle);
            telemetry.update();
            robot.frontRight.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
            robot.frontLeft.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
            robot.backRight.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
            robot.backLeft.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
            sleep(1);
        }
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);


    } */   //mecanumTimeDrive

    public double getAngle() {
        // We experimentally determined the Y axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - robot.lastAngles.firstAngle;

        if (deltaAngle < -180) {
            deltaAngle += 360;
        }
        else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }
        robot.globalAngle += deltaAngle;

        robot.lastAngles = angles;

        return robot.globalAngle;
    }
    public double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction;
        double gain = 0.025;

        //gain = mult * angle;

        correction = -getAngle() * gain;        // reverse sign of angle for correction.

        return correction;
    }
    public void resetAngle()
    {
        robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        robot.globalAngle = 0;
    }
    public void distanceTracking(double power){
        runtime1.reset();
    }
    public double getTrackingDistance(double power) {
        return runtime1.nanoseconds() * power * (robotSpeedinInchPerSec / 1_000_000_000);
    }
    public void initializeRobotWithIMU() {
        robot.init(hardwareMap);

        robot.imu.initialize(robot.parameters);

        //while (gamepad1.right_stick_x + gamepad1.right_stick_y != 1);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isSystemCalibrated())
        {
            sleep(50);
            idle();
        }
        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu+ calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        resetAngle();
        waitForStart();

        telemetry.update();

    }
    public void angleToSlopeConverter (double angle) {
        if (angle >= 0 || angle <= 90) {

        }
    }
    public void robotMotorController (double frontRight, double frontLeft, double backRight, double backLeft) {

        robot.frontRight.setPower(frontRight);
        robot.frontLeft.setPower(frontLeft);
        robot.backRight.setPower(backRight);
        robot.backLeft.setPower(backLeft);

    }
}

