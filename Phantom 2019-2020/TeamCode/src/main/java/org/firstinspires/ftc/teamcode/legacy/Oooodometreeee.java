package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.teamcode.MecanumConfig;

import java.util.Locale;

@Autonomous(name="Oooodometreeee",group="teleop")
public class Oooodometreeee extends LinearOpMode {

    Acceleration gravity;


        private ElapsedTime runtime = new ElapsedTime();
        MecanumConfig robot = new MecanumConfig();



        @Override
        public void runOpMode() {
            while (opModeIsActive()){
                /*
                gravity  = robot.imu.getGravity();
                String eeee = String.format(Locale.getDefault(), "%.3f",
                        Math.sqrt(gravity.xAccel*gravity.xAccel
                                + gravity.yAccel*gravity.yAccel
                                + gravity.zAccel*gravity.zAccel));
                telemetry.addLine();
                telemetry.addData("Debug1",eeee);
                telemetry.update();
                */

            }


        }
}
