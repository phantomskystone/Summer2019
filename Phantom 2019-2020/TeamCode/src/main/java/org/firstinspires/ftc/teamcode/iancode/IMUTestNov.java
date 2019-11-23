package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.*;

@Autonomous(name="IMU Test",group="autonomous")
public class IMUTestNov extends RedAuto{
    MecanumConfig robot = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        super.d();

        robot.init(hardwareMap);
        robot.doIMU();
        robot.imu.initialize(robot.parameters);
        robot.reverse();
        robot.doNewC();


        IMURedux MecanumInstance = new IMURedux(this,robot,0.025f);
        MecanumInstance.calibrate();
        ElapsedTime t = new ElapsedTime();
        t.reset();
        while (t.seconds()<3){MecanumInstance.mIMUddleDrive(0,0.3f);}

        robot.stop();
        t.reset();
        while (t.seconds()<3){MecanumInstance.mIMUddleDrive(90,0.3f);}
        robot.stop();
    }
}
