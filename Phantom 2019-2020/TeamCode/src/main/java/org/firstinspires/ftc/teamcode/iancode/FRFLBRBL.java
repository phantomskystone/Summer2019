package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumConfig;

@Autonomous(name="FRFLBRBL",group="autonomous")
public class FRFLBRBL extends LinearOpMode {
    MecanumConfig m = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        m.init(hardwareMap);

        //m.imu.initialize(m.parameters);
        MecanumRedux mr = new MecanumRedux(this);
        waitForStart();
        while (!isStopRequested()) {

            telemetry.addData("To start Press: ","A");
            if (gamepad1.a) {


                m.frontRight.setPower(0.5);
                sleep(2500);
                zeroMotors();
                sleep(2500);
                m.frontLeft.setPower(0.5);
                sleep(2500);
                zeroMotors();
                sleep(2500);
                m.backRight.setPower(0.5);
                sleep(2500);
                zeroMotors();
                sleep(2500);
                m.backLeft.setPower(0.5);
                sleep(2500);
                zeroMotors();
                sleep(2500);
                mr.drive(m, 0, 2.5f, 0.5f);
                sleep(2500);
                mr.drive(m, 0, 2.5f, -0.5f);
                break;
            }
            telemetry.update();
        }
    }
    public void zeroMotors(){
        m.frontRight.setPower(0);
        m.frontLeft.setPower(0);
        m.backLeft.setPower(0);
        m.backRight.setPower(0);
    }
}
