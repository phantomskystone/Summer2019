package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.iancode.MecanumRedux;

import java.lang.Math;

@Autonomous(name="Mecanum Method Auton Sigh",group="autonomous")

public class MecanumAutonSigh extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    MecanumConfig robot = new MecanumConfig();

    double power = .30, correction;

    double goAngle = 0;


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.doIMU();
        robot.imu.initialize(robot.parameters);
        MecanumRedux m = new MecanumRedux(this);
        //while (gamepad1.right_stick_x + gamepad1.right_stick_y != 1);

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
       // while (!isStopRequested() /*&& goAngle <= 360*/) {
            /*
            mecanumTimeDrive(goAngle, 2, 0.5);
            sleep(500);
            mecanumTimeDrive(goAngle, 2, -0.5);
            goAngle += 30;
            sleep(500);

             */
          //  mecanumTimeDrive(0, 1.1, 0.5);
            //m.drive(robot,0,1.1f,0.5f);
            m.simpleDrive(robot,1.1f,0.34f);
           // mecanumTimeDrive(90, 0.3, 0.5);
            sleep(5000);

            robot.armServo.setPosition((110/180));
            sleep(5000);
           // mecanumTimeDrive(0, 1.1, -0.5);
            //m.drive(robot,0,1.1f,-0.5f);
            m.simpleDrive(robot,1.1f,-0.34f);
            robot.armServo.setPosition(0); //TODO
            //ask Wesley

     //       break;
      //  }
    }
    private void mecanumTimeDrive(double angle, double timeS, double maxAllowedPower) {

        angle = -angle - 45;
        //for (angle;angle<)
        angle+=90;
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
        double gain = 0.025; //Default value?
        //double gain=0.00125;

        //gain = mult * angle;

        correction = -getAngle() * gain;        // reverse sign of angle for correction.

        return correction;
    }
    private void resetAngle()
    {
        robot.lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        robot.globalAngle = 0;
    }

}




