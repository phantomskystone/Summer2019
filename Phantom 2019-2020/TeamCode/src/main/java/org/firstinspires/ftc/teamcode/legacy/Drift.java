package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;;import org.firstinspires.ftc.teamcode.MecanumConfig;

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
        welcomeToTokeyo(35, 1, 2000);
    }
    void welcomeToTokeyo(int enterValueHere, double power, int time) {
        double xAxis = Math.cos(enterValueHere);
        double yAxis = Math.sin(enterValueHere);

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
    void drift(int factor, double maxAllowedPower) {



    }

}

