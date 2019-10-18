package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumConfig;
@Autonomous(name="Bridge",group="autonomous")
public class Bridge extends LinearOpMode {
    MecanumConfig m = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        m.init(hardwareMap);

        //m.imu.initialize(m.parameters);
        MecanumRedux mr = new MecanumRedux(this);
        waitForStart();
        mr.drive(m,0,1.1f,0.5f);
        m.armServo.setPosition((110/180));
        sleep(1000);
        mr.drive(m,0,1.5f,-0.5f);
        sleep(1000);
        m.armServo.setPosition(0);
        sleep(1000);
        mr.drive(m,90,1.1f,0.5f);
        mr.drive(m,0,0.6f,0.5f);
        mr.drive(m,90,1f,0.5f);
    }
}
