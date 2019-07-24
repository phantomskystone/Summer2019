package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "RevDistDrive",group = "Sensor")

public class SensorDistanceDrive extends LinearOpMode {

    PhantomConfig robot = new PhantomConfig();

    @Override
    public synchronized void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.rightDrive.setPower(0.3);
        robot.leftDrive .setPower(0.3);

        while (true) {

            if (robot.distanceSensor.getDistance(DistanceUnit.CM) <= 30) {

            break;

            }

        }

        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        stop();

    }
}
