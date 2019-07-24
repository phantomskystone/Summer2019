package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Mecanum Drive TeleOP")

public class MecanumDriveProgram extends LinearOpMode {

    MecanumConfig robot = new MecanumConfig();

    double frontLeft;
    double frontRight;
    double backLeft;
    double backRight;

    @Override
    public void waitForStart() {
    }

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);


        while (!isStopRequested()) {

            telemetry.addData("trigger", gamepad1.right_trigger);

            telemetry.addData("frontLeft",frontLeft);



            telemetry.update();



            if (gamepad1.dpad_up) {

                frontLeft = 1 - gamepad1.right_trigger;
                frontRight = 1 - gamepad1.right_trigger;
                backLeft = 1 - gamepad1.right_trigger;
                backRight = 1 - gamepad1.right_trigger;

            } else if (gamepad1.dpad_down) {

                frontLeft = -1 * (1 - gamepad1.right_trigger);
                frontRight = -1 * (1 - gamepad1.right_trigger);
                backLeft = -1 * (1 - gamepad1.right_trigger);
                backRight = -1 * (1 - gamepad1.right_trigger);

            } else if (gamepad1.dpad_left) {

                frontLeft = 1 - gamepad1.right_trigger;
                frontRight = -1 * (1 - gamepad1.right_trigger);
                backLeft = -1 * (1 - gamepad1.right_trigger);
                backRight = 1 - gamepad1.right_trigger;

            } else if (gamepad1.dpad_right) {

                frontLeft = -1 * (1 - gamepad1.right_trigger);
                frontRight = 1 - gamepad1.right_trigger;
                backLeft = 1 - gamepad1.right_trigger;
                backRight = -1 * (1 - gamepad1.right_trigger);

            } else {

                frontLeft = gamepad1.left_stick_y;
                backLeft = gamepad1.left_stick_y;
                frontRight = gamepad1.right_stick_y;
                backRight = gamepad1.right_stick_y;

            }
            robot.frontLeft.setPower(frontLeft);
            robot.backLeft.setPower(backLeft);
            robot.backRight.setPower(backRight);
            robot.frontRight.setPower(frontRight);

        }
    }
}
