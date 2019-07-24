package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.lang.Math;


/** be sure remove all lines of code with -/***/
/** in front of them when using this method in a program ***/

@Autonomous(name="Mecanum Method Auton",group="autonomous")

public class MecanumAuton extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    MecanumConfig robot = new MecanumConfig();

    @Override
    public void waitForStart() {
    waitForStart();
    }
    double goAngle = 0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        while (gamepad1.right_stick_x + gamepad1.right_stick_y != 1);



        while (goAngle <= 360) {

            mecanumTimeDrive(goAngle, 1, 0.5);
            sleep(500);
            mecanumTimeDrive(goAngle, 1, -0.5);
            goAngle += 30;
            sleep(500);
        }
    }
    private void mecanumTimeDrive(double angle, double timeS, double maxAllowedPower) {

        int timer = (int)(timeS*1000);

        angle = angle + 45;

        robot.frontRight.setPower((Math.sin(Math.toRadians(angle))* maxAllowedPower)+(checkDirection()/2));
        robot.frontLeft.setPower((Math.cos(Math.toRadians(angle))* maxAllowedPower)-(checkDirection()/2));
        robot.backRight.setPower((Math.cos(Math.toRadians(angle))* maxAllowedPower)+(checkDirection()/2));
        robot.backLeft.setPower((Math.sin(Math.toRadians(angle))* maxAllowedPower)-(checkDirection()/2));
        sleep(timer);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);

                }
                private double getAngle()
    {
        // We experimentally determined the Y axis is the axis we want to use for heading angle.
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
    private double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle;
        double gain = 0.05;
        angle = getAngle();

        //gain = mult * angle;

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

}




