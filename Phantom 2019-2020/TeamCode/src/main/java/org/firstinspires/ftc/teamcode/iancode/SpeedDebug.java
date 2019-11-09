package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.RedAuto;
@Disabled
@Autonomous(name="speedDebug",group="debug")
public class SpeedDebug extends RedAuto {
    MecanumConfig robot=new MecanumConfig();
    MecanumRedux2 m = new MecanumRedux2(this);
    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        robot.reverse();
        robot.doIMU();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("Is the IMU bad?", robot.imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart();
        //dothething
        //1
        while(!gamepad1.a && !isStopRequested());
        m.middleDrive(robot,0,1);
        sleep(1000);
        Velocity V = robot.imu.getVelocity();
        sleep(1000);
        robot.stop();
        velocityOut(V);
        while(!gamepad1.a && !isStopRequested());
        m.middleDrive(robot,0,0.75f);
        sleep(1000);
        V = robot.imu.getVelocity();
        sleep(1000);
        robot.stop();
        velocityOut(V);
        while(!gamepad1.a && !isStopRequested());
        m.middleDrive(robot,0,0.5f);
        sleep(1000);
        V = robot.imu.getVelocity();
        sleep(1000);
        robot.stop();
        velocityOut(V);
        while(!gamepad1.a && !isStopRequested());
        m.middleDrive(robot,0,0.25f);
        sleep(1000);
        V = robot.imu.getVelocity();
        sleep(1000);
        robot.stop();
        velocityOut(V);

    }
    public void velocityOut(Velocity In){
        telemetry.addData("X",In.xVeloc);
        telemetry.addData("Y",In.yVeloc);
        telemetry.addData("Z",In.zVeloc);
        telemetry.update();
    }

}
