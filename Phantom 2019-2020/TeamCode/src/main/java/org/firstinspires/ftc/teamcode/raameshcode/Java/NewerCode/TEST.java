package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.SeasonCode.Controllers.ProtoReduxController2X;
import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;

@Autonomous(name = "TEST ")

public class TEST extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();

    ProtoReduxController2X proto = new ProtoReduxController2X(this);

    double angle = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while (!isStopRequested()) {
            proto.middleDrive(robot,angle,0.3);
            wait(1500);
            angle += 60;
        }
    }
}
