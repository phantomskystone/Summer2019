package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Raamesh TeleOP", group = "Teleop")

public class Raamesh_Teleop extends OpMode {
    PhantomConfig robot = new PhantomConfig();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop() {}

    @Override
    public void start() {}

    @Override
    public void loop() {
        double  left;
        double right;
        double intake = robot.intakeServo.getPosition();

        right = gamepad1.right_stick_y;
        left = gamepad1.left_stick_y;

        if (gamepad1.a) {
            intake += 0.4;
        }
        else if (gamepad1.y) {
            intake -= 0.4;
        }
        else if (gamepad1.left_bumper) {
            intake += 0.1;
        }
        else if (gamepad1.right_bumper) {
            intake -= 0.1;
        }
        else {
            intake = 0.5;
        }

        robot.intakeServo.setPosition(intake);

        robot.leftDrive.setPower(-left);
        robot.rightDrive.setPower(-right);
    }

    @Override
    public void stop() {}

}
