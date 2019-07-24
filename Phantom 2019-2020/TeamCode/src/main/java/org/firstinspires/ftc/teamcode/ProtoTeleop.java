package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Proto Teleop",group="Teleop")
public class ProtoTeleop extends OpMode {

    PhantomConfig robot = new PhantomConfig();
    private ElapsedTime runtime1 = new ElapsedTime();
    private ElapsedTime runtime2 = new ElapsedTime();
    double arm = 0;
    double runt;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        double left;
        double right;
        double climb;
        boolean armMove = false;
        boolean armExtend = true;
        boolean armUp = true;
        boolean armMovingUp = false;
        String move = "no";

        double intake = robot.intakeServo.getPosition();
        double slide = robot.slideServo.getPosition();

        right = gamepad1.left_stick_y;
        left = gamepad1.right_stick_y;
        climb = gamepad2.left_stick_y;

        if (!armMove) {
            if (!armUp) {
                if (gamepad2.right_stick_y < 0) {
                    runtime1.reset();
                    runt = 1.75;
                    arm = 1;
                    armMovingUp = true;
                    armMove = true;
                    intake = 0.5;


                }
            }
            if (armUp) {
                if (gamepad2.right_stick_y > 0) {
                    runtime1.reset();
                    arm = -1;
                    armMove = true;
                    intake = 0.5;
                    runt = 1.5;

                }
            }
        }
        if (runtime1.seconds() >= runt) {
            arm = 0;
            armMove = false;
            if (armMovingUp) {
                armUp = true;
                armMovingUp = false;
            }

        }
            if (gamepad2.left_bumper) {
                intake += 0.1;
            } else if (gamepad2.right_bumper) {
                intake -= 0.1;
            } else {
                intake = 0.5;
            }

            if (armExtend) {
                if (gamepad2.dpad_up) {
                    runtime2.reset();
                    slide = 0;
                    armExtend = false;
                }
                else if (gamepad2.dpad_down) {
                    runtime2.reset();
                    slide = 1;
                    armExtend = false;
                }

            }
            if (runtime2.seconds() >= 6) {
                armExtend = true;
                slide = 0.49;
            }
            robot.leftDrive.setPower(left);
            robot.rightDrive.setPower(right);
            robot.armMotor.setPower(arm);

            robot.climbMotor.setPower(climb);


            robot.intakeServo.setPosition(intake);
            robot.slideServo.setPosition(slide);
            telemetry.addData("slide", slide);
            telemetry.addData("slide servo", robot.slideServo.getPosition());
            telemetry.addData("intake", intake);
            telemetry.addData("intake servo", robot.intakeServo.getPosition());
            telemetry.update();
            System.out.println(robot.slideServo.getPosition());
        }




    @Override
    public void stop() {
    }
}



