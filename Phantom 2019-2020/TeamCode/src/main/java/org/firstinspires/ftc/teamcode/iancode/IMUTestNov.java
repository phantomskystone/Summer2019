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
        IMURedux MecanumInstance = new IMURedux(this,robot,0.025f);
        MecanumInstance.calibrate();
        robot.reverse();
        robot.doNewC();



        waitForStart();
        ElapsedTime t = new ElapsedTime();
        t.reset();
        while (!isStopRequested()) {
            MecanumInstance.imuDrive(90,3,0.6);
            sleep(300);
            MecanumInstance.imuDrive(-90,3,0.6);
        }
        //MecanumInstance.imuDrive(0,3,0.6);


        //while (t.seconds()<3){MecanumInstance.mIMUddleDrive(0,0.3f);}

        //robot.stop();
        //t.reset();
        //while (t.seconds()<3){MecanumInstance.mIMUddleDrive(90,0.3f);}
        robot.stop();
    }
}
