package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="Raamesh 'Proto'")

public class auton extends LinearOpMode {

    config robot =  new config();



    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.leftDrive. setPower(1.0);
        robot.leftDrive. setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightDrive.setPower(1.0);
        robot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(1000);
        robot.leftDrive. setPower(0);
        robot.rightDrive.setPower(0);
    }

}
