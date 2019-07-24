package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "MecanumDrive2")
public class Mecanum2 extends OpMode {

    MecanumConfig robot = new MecanumConfig();

    double frontLeft;
    double frontRight;
    double backLeft;
    double backRight;
    double side;

    @Override
    public void init() {robot.init(hardwareMap);}

    @Override
    public void init_loop() {}

    @Override
    public void start() {}

    @Override
    public void loop() {
        side = (gamepad1.left_stick_x + gamepad1.right_stick_x)/2;
        frontLeft = -gamepad1.left_stick_y - side;
        backLeft  = -gamepad1.left_stick_y + side;
        frontRight= -gamepad1.right_stick_y + side;
        backRight = -gamepad1.right_stick_y - side;
    }
}

