package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.Odometry.Tracker;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;

public class Odemetry extends LinearOpMode implements Runnable{

    MecanumConfig robot = new MecanumConfig();

    ElapsedTime disttimeTracking = new ElapsedTime();

    public volatile boolean isTreadStarted = false;

    protected volatile double time = 0;

    protected volatile boolean isThreadStopRequested = false;


    public volatile String threadName;
    public volatile double distance = 0;
    public volatile double initalVeloc = 0;
    public volatile double initialDist = 0;

    public Odemetry(String Odometry) {
        threadName = "Distance";
    }
    public void run() {
        if (!isStopRequested()) {
            isTreadStarted = true;
            disttimeTracking.reset();
            resetDistance();
            isThreadStopRequested = false;
            while (!isStopRequested() && !isThreadStopRequested) {
                distance = ((initalVeloc * disttimeTracking.seconds()) + ((1 / 2) * Math.sqrt(robot.Accel.xAccel * robot.Accel.xAccel + robot.Accel.yAccel * robot.Accel.yAccel) * (disttimeTracking.seconds() * disttimeTracking.seconds())) + initialDist)*0.0254;
                initalVeloc = (distance + -(((1 / 2) * Math.sqrt(robot.Accel.xAccel * robot.Accel.xAccel + robot.Accel.yAccel * robot.Accel.yAccel) * (disttimeTracking.seconds() * disttimeTracking.seconds())) + initialDist)) / disttimeTracking.seconds();
                initialDist = distance;
                telemetry.addData("Distance", distance);
                telemetry.addData("Velocity", initalVeloc);
            }
            isTreadStarted = false;
        }
    }

    public void runOpMode() throws InterruptedException {

    }
    public double getDistance(){
        return distance;
    }
    public boolean getStart(){
        return isTreadStarted;
    }
    public void setStop(boolean b){
        isThreadStopRequested=b;
    }
    public void resetDistance() {
        distance = 0;
    }

}
