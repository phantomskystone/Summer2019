package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.SeasonCode.Controllers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.Odometry.Tracker.Odemetry;
import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;


public class DistTracker extends LinearOpMode {

    public void runOpMode() {}

    MecanumConfig robot = new MecanumConfig();

    Odemetry domo = new Odemetry("odo");
    Thread t = new Thread(domo);

    public double Distance;

    public void distanceTracking() {
        if (domo.getStart()) {
            t.interrupt();
            domo.setStop(domo.getStart());
        }
        t.run();
    }
    public double getTrackingDistance() {
        Distance = domo.getDistance();
        return Distance;
    }


}
