package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumConfig;

public class Arch extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();

    @Override public void waitForStart () {
        telemetry.addLine("Waiting For Start");
        waitForStart();
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        archMove(10);
    }
    private void archMove(double width) {

    }
}
