package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Wesley Mecanum Angle Auton", group = "Autonomous")

public class Drift extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();

    @Override
    public void waitForStart() {
        waitForStart();
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        strafeAtAngle(35, 1, 2000);
    }
    void strafeAtAngle(int angle, double power, int time) {
        double xAxis = Math.cos(angle);
        double yAxis = Math.sin(angle);

        robot.frontLeft.setPower(power*(-yAxis - xAxis));
        robot.backLeft.setPower(power*(-yAxis + xAxis));
        robot.frontRight.setPower(power*(-yAxis + xAxis));
        robot.backRight.setPower(power*(-yAxis - xAxis));

        sleep(time);

        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    }
}
