package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name="Pull Back 1",group="autonomous")

public class PullBack extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }
//    private ElapsedTime runtime = new ElapsedTime();
//    MecanumConfig robot = new MecanumConfig();
//
//    double power = .30, correction;
//
//    double goAngle = 0;
//
//
//    @Override
//    public void runOpMode() {
//
//
//
//        waitForStart();
//
//
//
//
//        while (!isStopRequested()) {
//            mecanumTimeDrive(0, 5, -0.25);
//            sleep(500);
//            //mecanumTimeDrive(goAngle, 2, -0.5);
//            //goAngle += 30;
//            //sleep(500);
//        }
//        requestOpModeStop();
//        stop();
//    }
//    public void mecanumTimeDrive(double angle, double timeS, double maxAllowedPower) {
//
//        angle = -angle - 45;
//
//        runtime.reset();
//        while (!isStopRequested() && runtime.seconds() < timeS) {
//            telemetry.addData("Angle X", getAngle());
//            telemetry.addData("Correction", checkDirection());
//            telemetry.addData("lastangles", robot.lastAngles.firstAngle);
//            telemetry.update();
//            robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower));
//            robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower));
//            robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower));
//            robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower));
//            sleep(1);
//        }
//        robot.frontRight.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);
//        robot.backLeft.setPower(0);
//
//
//    }   //mecanumTimeDrive
//
//    private double getAngle() {
//        // We experimentally determined the Y axis is the axis we want to use for heading angle.
//        // We have to process the angle because the imu works in euler angles so the Z axis is
//        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
//        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.
//
//        Orientation angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        double deltaAngle = angles.firstAngle - robot.lastAngles.firstAngle;
//
//        if (deltaAngle < -180) {
//            deltaAngle += 360;
//        }
//        else if (deltaAngle > 180) {
//            deltaAngle -= 360;
//        }
//        robot.globalAngle += deltaAngle;
//
//        robot.lastAngles = angles;
//
//        return robot.globalAngle;
//    }
//    private double checkDirection()
//    {
//        // The gain value determines how sensitive the correction is to direction changes.
//        // You will have to experiment with your robot to get small smooth direction changes
//        // to stay on a straight line.
//        double correction;
//        double gain = 0.025;
//
//        //gain = mult * angle;
//
//        correction = -getAngle() * gain;        // reverse sign of angle for correction.
//
//        return correction;
//    }
//    private void resetAngle()
//    {
//       // robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        robot.globalAngle = 0;
//    }

}




