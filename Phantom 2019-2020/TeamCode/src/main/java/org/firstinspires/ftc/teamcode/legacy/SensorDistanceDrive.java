package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
