package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumConfig;

@Autonomous(name="Bridge (BLUE)",group="autonomous")
public class BridgeBlue extends LinearOpMode {
    MecanumConfig robot = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        //robot.imu.initialize(robot.parameters);
        MecanumRedux MecanumInstance = new MecanumRedux(this);
        waitForStart();
        MecanumInstance.drive(robot,0,1.1f,0.5f);
        robot.armServo.setPosition((110/180));
        sleep(500);
        MecanumInstance.drive(robot,0,1.5f,-0.5f);
        sleep(500);
        robot.armServo.setPosition(0);
        sleep(500);
        MecanumInstance.drive(robot,90,1.1f,0.5f);
        MecanumInstance.drive(robot,0,0.6f,0.5f);
        MecanumInstance.drive(robot,90,1f,0.5f);
        
    }
}
