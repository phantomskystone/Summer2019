package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.Odometry.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.Odometry.Tracker.Odemetry;
import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;

public class OdometryTest extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();
    Odemetry domeetry = new Odemetry("Odo");



    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        robot.imu.initialize(robot.parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isSystemCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart();
        domeetry.run();
        while (!isStopRequested()) {
            telemetry.addData("Distance",domeetry.distance);
            telemetry.addData("Velocity",domeetry.initalVeloc);
        }
    }
}
