package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.SeasonCode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.SeasonCode.Controllers.ProtoReduxController2X;
import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;

@Autonomous(name = "oDometryTEST")
public class domoTEST extends LinearOpMode {
    ProtoReduxController2X redux = new ProtoReduxController2X(this);
    MecanumConfig robot = new MecanumConfig();
     ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.doIMU();
        telemetry.addData("Status","Calibrating");
        telemetry.update();
        robot.imu.initialize(robot.parameters);
        while (!isStopRequested() && !robot.imu.isAccelerometerCalibrated() && runtime.seconds() < 5) {
            idle();
        }
        telemetry.addData("Status","Done Calibrating, Waiting For Start");
        telemetry.update();
        waitForStart();
        telemetry.update();
        redux.stopBreakingMyMindDrive(robot,1,0,1);
    }
}
