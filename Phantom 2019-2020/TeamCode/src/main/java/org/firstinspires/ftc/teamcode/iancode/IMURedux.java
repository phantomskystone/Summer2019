package org.firstinspires.ftc.teamcode.iancode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.MecanumConfig;

public class IMURedux extends MecanumRedux2 {

    MecanumConfig robot;
    public IMURedux(LinearOpMode thisOpMode, MecanumConfig robot){
        e=thisOpMode;
        this.robot=robot;
    }

    public void resetAngle()
    {
        robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        robot.globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    public double getAngle()
    {
        if (robot.IMUf) {


            // We experimentally determined the Z axis is the axis we want to use for heading angle.
            // We have to process the angle because the imu works in euler angles so the Z axis is
            // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
            // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

            // Who wrote the above comment? - Ian


            // is this the correct angle? Th Controller is mounted on it's side, so theoretically we need to use the X axis.  -Ian
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
        return 0;
    }
    public void imuDrive(int angle, double timeS, double maxAllowedPower) {
        if (robot.IMUf) {
            angle = angle+45;
            ElapsedTime runtime = new ElapsedTime();
            runtime.reset();
            while (!e.isStopRequested() && runtime.seconds() < timeS) {
                e.telemetry.addData("Angle X", getAngle());
                e.telemetry.addData("Correction", checkDirection());
                e.telemetry.addData("lastangles", robot.lastAngles.firstAngle);
                e.telemetry.update();
                robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
                robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
                robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * maxAllowedPower) - checkDirection());
                robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * maxAllowedPower) + checkDirection());
                e.sleep(1);
            }
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
            robot.frontLeft.setPower(0);
            robot.backLeft.setPower(0);
        }


    }   //mecanumTimeDrive
    public void mIMUdleDrive(int angle, float power){
        if (robot.IMUf) {
            e.telemetry.addData("Angle X", getAngle());
            e.telemetry.addData("Correction", checkDirection());
            e.telemetry.addData("lastangles", robot.lastAngles.firstAngle);
            e.telemetry.update();
            robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * power) - checkDirection());
            robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * power) + checkDirection());
            robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * power) - checkDirection());
            robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * power) + checkDirection());
        }
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
}
