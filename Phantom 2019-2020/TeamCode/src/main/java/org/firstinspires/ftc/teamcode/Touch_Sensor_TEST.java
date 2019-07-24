package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.math.MathContext;

@Autonomous(name = "Touch Sensor Test", group = "Sensor")

public class Touch_Sensor_TEST extends LinearOpMode {

    DevConfig robot = new DevConfig();


    @Override
    public synchronized void waitForStart() {
        super.waitForStart();
        while (!isStopRequested() && !robot.imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }
    }

    @Override
    public void runOpMode()/* throws InterruptedException */{

        double touchAmount = 0;

        //  robot.imu.getParameters();
        robot.init(hardwareMap);

        robot.imu.writeCalibrationData(BNO055IMU.CalibrationData.deserialize(toString()));

        double angle;

        while (touchAmount != 4) {
            robot.rightDrive.setPower(0.2);
            robot.leftDrive.setPower(0.2);

            while (!robot.touchSensor.getState())

                touchAmount = touchAmount + 1;

            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            robot.encoderDrive(20, -10, -10, 20);

            robot.leftDrive.setPower(-0.3);

            // turn 90 degrees right.

            robot.rotate(100,.5);

        }

    }
}
