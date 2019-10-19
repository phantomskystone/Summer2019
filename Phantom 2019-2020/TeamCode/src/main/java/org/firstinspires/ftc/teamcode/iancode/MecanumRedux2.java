package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumConfig;

public class MecanumRedux2 extends MecanumRedux {
    public MecanumRedux2(LinearOpMode thisOpmode){
        e=thisOpmode;
    }
    public void middleDrive(MecanumConfig robot, int angle, float power){
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        angle=angle+45; // this does the thing. Magic numbers are magic. -Ian
        //this should work, however we need a controller input to test
        //while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
        robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * power)); //We need a weight fix, if only we had a center of mass overlay.
        robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * power));
    }
    public void drive(MecanumConfig robot, int angle, double power, double time){
        super.drive(robot,angle,(float)time,(float)power);
    }
    public void complexDrive(MecanumConfig robot, int angle, double distance, double power){
        //do not ever do this
        robot.stop();
        e.telemetry.addData("ERROR", "Unknown Opcode");
        e.telemetry.update();
        e.requestOpModeStop();
        e.stop();
    }
}
