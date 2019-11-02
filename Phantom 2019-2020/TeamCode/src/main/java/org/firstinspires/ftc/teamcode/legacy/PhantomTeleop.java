package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Phantom Teleop",group="Teleop")
public class PhantomTeleop extends OpMode {
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

        double left;
        double right;
        double climb;
        double arm;

        double intake = robot.intakeServo.getPosition();
        double slide  = robot.slideServo.getPosition();

        right  = gamepad1.left_stick_y;
        left = gamepad1.right_stick_y;
        climb = gamepad2.left_stick_y;
        arm = -gamepad2.right_stick_y/4;
        if (gamepad2.left_bumper) {
            intake += 0.1;
        }
        else if (gamepad2.right_bumper) {
            intake -= 0.1;
        }
        else {
            intake = 0.5;
        }
        if (gamepad2.dpad_up) {
            slide += 0.1;
        }
        else if (gamepad2.dpad_down) {
            slide -= 0.1;
        }
        else {
            slide = 0.49;
        }
        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);
        robot.armMotor.setPower(arm);

        robot.climbMotor.setPower(climb);
        robot.armMotor.setPower(arm);

        robot.intakeServo.setPosition(intake);
        robot.slideServo.setPosition(slide);
        telemetry.addData("slide", slide);
        telemetry.addData("slide servo", robot.slideServo.getPosition());
        telemetry.addData("intake", intake);
        telemetry.addData("intake servo", robot.intakeServo.getPosition());
        telemetry.update();
    }

    @Override
    public void stop() {}
}
