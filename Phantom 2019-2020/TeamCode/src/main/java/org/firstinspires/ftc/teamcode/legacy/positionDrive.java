package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "IMU Distance Sensor?",group = "Autonomous")

public class positionDrive extends LinearOpMode {

//
//    Orientation angles;
//    Acceleration gravity;
//
//
//    //in meters
//
//    public double distance;
//
//    MecanumConfig robot = new MecanumConfig();
//
//
//    @Override
//    public void runOpMode() {
//
//        robot.init(hardwareMap);
//
//        robot.imu.initialize(robot .parameters);
//
//        telemetry.addData("Mode", "calibrating...");
//        telemetry.update();
//
//        // make sure the imu gyro is calibrated before continuing.
//        while (!isStopRequested() && !robot.imu.isSystemCalibrated())
//        {
//            telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
//            telemetry.update();
//            sleep(50);
//            idle();
//        }
//
//        telemetry.addData("Mode", "waiting for start");
//        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
//        telemetry.update();
//
//
//        robot.parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        robot.parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        robot.parameters.loggingEnabled      = false;
//
//        angles   = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//        gravity  = robot.imu.getGravity();
//
//        // wait for start button.
//        waitForStart();
//
//        telemetry.addLine("running");
//        telemetry.update();
//
//        sleep(500);
//
//        while (!isStopRequested()) {
//           telemetry.addData("distance",distance);
//           telemetry.update();
//        }
//
//    }
//
//}
////class  IMU_Distance extends LinearOpMode implements Runnable{
//
// //   public Orientation angles;
//   // public Acceleration gravity;
//
//
//   // MecanumConfig robot = new MecanumConfig();
//    //in meters
//
//   // public double distance;
//
//   // Thread Distance;
//   // int delay;
//
//   /* IMU_Distance (int delay) {
//        Distance = new Thread(this,"My thread");
//        this.delay = delay;
//        Distance.start();
//    }
//    /*@Override public void runOpMode() {
//        robot.init(hardwareMap);
//    }
//    public void run() {
//        double change = robot.imu.getLinearAcceleration()*00.2;
//        while (!isStopRequested()) {
//
//             distance += robot.imu.getLinearAcceleration()*00.2;
//            sleep(2);
//        }

    @Override
    public void runOpMode() throws InterruptedException {

    }
}


