package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "IMU Distance Sensor? Re-Discovered",group = "Autonomous")

public class positionDrive2 extends LinearOpMode {


    Orientation angles;
    Acceleration gravity;




    //in meters
    double distanceX;
    double distanceY;
    double distanceZ;

    IMU_Distance meter = new IMU_Distance(0);

    MecanumConfig robot = new MecanumConfig();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.imu.initialize(robot.parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isSystemCalibrated())
        {
            telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
            telemetry.update();
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();


        robot.parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        robot.parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        robot.parameters.loggingEnabled      = false;

        angles   = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity  = robot.imu.getGravity();

        // wait for start button.
        waitForStart();

        telemetry.addLine("running");
        telemetry.update();

        sleep(500);

        while (!isStopRequested()) {
            telemetry.addAction(new Runnable() { @Override public void run()
            {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                angles   = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity  = robot.imu.getLinearAcceleration();
            }
            });
            sleep(5);
            distanceX += gravity.xAccel/50;
            sleep(5);
            distanceY += gravity.yAccel/50;
            sleep(5);
            distanceZ += gravity.zAccel/50;
            sleep(5);
           telemetry.addData("distance X",distanceX);
           telemetry.addData("distance Y", distanceY);
           telemetry.addData("distance Z",distanceZ);
           telemetry.update();
        }

    }

}