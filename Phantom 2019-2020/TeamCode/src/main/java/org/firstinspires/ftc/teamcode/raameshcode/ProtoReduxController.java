package org.firstinspires.ftc.teamcode.raameshcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumConfig;

public class ProtoReduxController extends LinearOpMode {

    public void runOpMode() {}

    MethodsWithoutVuforia methods = new MethodsWithoutVuforia();

    LinearOpMode linearOpMode;
    boolean debug=true;
    public ProtoReduxController(){


    }
    public void drive(MecanumConfig robot, double angle, double time, double power){
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        angle=angle+45; // this does the thing. Magic numbers are magic. -Ian
        //this should work, however we need a controller input to test
        //while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
            robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * power)); //We need a weight fix, if only we had a center of mass overlay.
            robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * power));
            robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * power));
            robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * power));

        //}


        while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
            if (debug) {


                linearOpMode.telemetry.addData("Timestamp: ", System.nanoTime());
                linearOpMode.telemetry.addData("Front Right: ", Math.sin(Math.toRadians(angle)) * power);
                linearOpMode.telemetry.addData("Front Left: ", Math.cos(Math.toRadians(angle)) * power);
                linearOpMode.telemetry.addData("Back Right: ", Math.cos(Math.toRadians(angle)) * power);
                linearOpMode.telemetry.addData("Back Left: ", Math.sin(Math.toRadians(angle)) * power);
                linearOpMode.telemetry.update();
            }
        } //TODO
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }
    public void simpleDrive(MecanumConfig robot, double time, double power){
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        //angle=angle+45; // this does the thing. Magic numbers are magic. -Ian
        //this should work, however we need a controller input to test
        //while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
        robot.frontRight.setPower(power); //We need a weight fix, if only we had a center of mass overlay.
        robot.frontLeft.setPower(power);
        robot.backRight.setPower(power);
        robot.backLeft.setPower(power);

        //}
        while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
            if (debug) {


                linearOpMode.telemetry.addData("Timestamp: ", System.nanoTime());
                linearOpMode.telemetry.addData("Front Right: ",  power);
                linearOpMode.telemetry.addData("Front Left: ", power);
                linearOpMode.telemetry.addData("Back Right: ", power);
                linearOpMode.telemetry.addData("Back Left: ", power);
                linearOpMode.telemetry.update();
            }
        } //TODO
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }
    /** This literally does the same thing as above. Good job. 10/10 -Ian
    public void driveSwitch(MecanumConfig robot, double angle, double time, double power){
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        angle=-angle-45; // Wesley wrote this!
        angle=angle+90; //Magic numbers!
        //this should work, however we need a controller input to test
        while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
            robot.frontRight.setPower((Math.cos(Math.toRadians(angle)) * power));
            robot.frontLeft.setPower((Math.sin(Math.toRadians(angle)) * power));
            robot.backRight.setPower((Math.sin(Math.toRadians(angle)) * power));
            robot.backLeft.setPower((Math.cos(Math.toRadians(angle)) * power));

        }
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }

     **/
    public void middleDrive(MecanumConfig robot, double angle, double power){
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        angle=angle+45; // this does the thing. Magic numbers are magic. -Ian
        //this should work, however we need a controller input to test
        //while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
        robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * power)); //We need a weight fix, if only we had a center of mass overlay.
        robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * power));
        }
    public void complexDrive(MecanumConfig robot, double angle, double distance, double power){
        methods.distanceTracking(power);
        ElapsedTime runtime = new ElapsedTime();runtime.reset();
        runtime.reset();
        angle = angle + 45; // this does the thing. Magic numbers are magic. -Ian
        //this should work, however we need a controller input to test
        //while (runtime.seconds() < time && !linearOpMode.isStopRequested()){
        robot.frontRight.setPower((Math.sin(Math.toRadians(angle)) * power)); //We need a weight fix, if only we had a center of mass overlay.
        robot.frontLeft.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backRight.setPower((Math.cos(Math.toRadians(angle)) * power));
        robot.backLeft.setPower((Math.sin(Math.toRadians(angle)) * power));
        while (methods.trackingDistance < distance);
        robot.stop();
    }
    public void stopBreakingMyMindDrive (MecanumConfig robot, double Δx,double Δy,double power) {
        double stopBreakingMyMindDriveAngle;
        if (Δy == 0) {
            if (Δx > 0) {
                stopBreakingMyMindDriveAngle = 90;
            } else {
                if (Δx < 0) {
                    stopBreakingMyMindDriveAngle = -90;
                } else {
                    throw new IllegalArgumentException("no position change @ ''stopBreakingMyMindDrive'' Method");
                }
            }
        }
        if (Δx == 0) { if (Δy > 0) {
            stopBreakingMyMindDriveAngle = 0;
        } else {stopBreakingMyMindDriveAngle = 180;}
        } else {
            if (Δx > 0) {
                stopBreakingMyMindDriveAngle = Math.atan(Δy/Δx);
            } else {
                stopBreakingMyMindDriveAngle = -Math.atan(Δy/Δx);
            }
            if (Δy < 0) {
                if (Δx > 0) {
                    stopBreakingMyMindDriveAngle = stopBreakingMyMindDriveAngle + 90;
                } else {
                    stopBreakingMyMindDriveAngle = stopBreakingMyMindDriveAngle - 90;
                }
            }
        }


        complexDrive(robot,stopBreakingMyMindDriveAngle,Math.sqrt((Δx*Δx) + (Δy*Δy)),power);
    }
    public void vuForiaDriveSegment () {

    }

}
