package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name="Mecanum Method Auton + IMU",group="autonomous")

public class MecanumAutonVuforia extends LinearOpMode {
/*
    private ElapsedTime runtime = new ElapsedTime();
    MecanumConfig robot = new MecanumConfig();

    double power = .30, correction;

    double goAngle = 0;
    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
/*
    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.imu.initialize(robotx.parameters);

        //while (gamepad1.right_stick_x + gamepad1.right_stick_y != 1);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

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
    /*
        parameters.vuforiaLicenseKey = "AahhGez/////AAABmSIunC5QeUbDmqSuN/JVvK5jcN03J60dszlHmZqGkfuUkUVQsH4/YxQk1A+Rg/DlMP1LwU5U/g4EW5+j6271bFI0ZWGQScblmtv/MVEAgzJqZdZkHl/ZS8qgIDkPDzKJLCJwz9qtN6fKZJ5FC0uxuQ9vHblm5dyr0iIAUM78c6Wc2fzqDmTm3WuOFbBthCKJRJNpfhUn9CyA1Wdev+LzIbotSs++L3a9O4ekv1/NKi5Khujw3L2i1IxY8M2hZlTYfeCn57W4bCxnfSWChH0yNv7G5gk3jyMP5d8pR1XtqDu4kqx+dQ2/Fq+M9Fdun8nfE5peCIVk592Ul+sjhC5Nr8FQZIFfxoAJIAWu3o4QizOb";

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu+ calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart();

        telemetry.update();

        resetAngle();
        while (!isStopRequested() && goAngle <= 360) {
            mecanumTimeDrive(goAngle, 2, 0.5);
            sleep(500);
            mecanumTimeDrive(goAngle, 2, -0.5);
            goAngle += 30;
            sleep(500);
        }
    }
    public void mecanumTimeDrive(double angle, double timeS, double maxAllowedPower) {

        angle = -angle - 45;

        runtime.reset();
        while (!isStopRequested() && runtime.seconds() < timeS) {
            telemetry.addData("Angle X", getAngle());
            telemetry.addData("Correction", checkDirection());
            telemetry.addData("lastangles", robot.lastAngles.firstAngle);
            telemetry.update();
            robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
            robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
            robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
            robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
            sleep(1);
        }
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);


    }   //mecanumTimeDrive

    private double getAngle() {
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
    private double checkDirection()
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
    private void resetAngle()
    {
        robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        robot.globalAngle = 0;
    }
    */

    @Override
    public void runOpMode() throws InterruptedException {

    }
}




