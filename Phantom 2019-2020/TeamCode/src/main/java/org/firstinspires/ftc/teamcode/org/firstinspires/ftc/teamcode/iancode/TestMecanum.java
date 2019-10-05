package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumConfig;

@Autonomous(name="Look Ma, no IMU!",group="autonomous")

public class TestMecanum extends LinearOpMode {
    MecanumConfig m = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        float power=0.5f;
        MecanumRedux Redux = new MecanumRedux(this);
        m.init(hardwareMap);
        waitForStart();
        sleep(2500);
        telemetry.addData("Test","1");
        telemetry.update();
        Redux.driveSwitch(m,0,2,power);
        telemetry.clearAll();
        sleep(2500);
        telemetry.addData("Test","2");
        telemetry.update();
        Redux.driveSwitch(m,90,2,power);
        telemetry.clearAll();
       // sleep(2500);
       // telemetry.addData("Test","3");
       // telemetry.update();
       // MecanumRedux.drive(m,(-90 - 45),1,0.35f);
       // telemetry.clearAll();
        sleep(2500);
        telemetry.addData("Test","3");
        telemetry.update();
        Redux.driveSwitch(m,(45),2,power);
        telemetry.clearAll();
        requestOpModeStop();
        stop();

    }

}
