package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
public class Tracker {
    BNO055IMU V;
    public Tracker(BNO055IMU imu){
        V=imu;

    }
    public double getNanos(){
        return System.nanoTime();
    }
    public double getNanoDiffs(double nanos){
        return getNanos()-nanos;
    }
    public double[] Track(double nanos){
        if (V!=null){
            Acceleration E=V.getAcceleration();
            double seconds = nanos / 1_000_000_000.0;
            double xVel = E.xAccel /seconds;
            double yVel = E.yAccel/seconds;
            double zVel = E.zAccel/seconds;
            return new double[] {xVel,yVel,zVel};
        }
        return null;
    }

}
