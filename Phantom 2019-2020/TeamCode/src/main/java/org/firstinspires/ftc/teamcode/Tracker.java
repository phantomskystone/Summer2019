package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;

import java.util.Locale;



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
        public String Track(double nanos){
            if (V!=null){
                org.firstinspires.ftc.robotcore.external.navigation.Acceleration E=V.getAcceleration();
                double seconds = nanos / 1000000000.0;
                double xVel = E.xAccel /seconds;
                double yVel = E.yAccel/seconds;
                double zVel = E.zAccel/seconds;
                Acceleration gravity = V.getGravity();
                String ret = String.format(Locale.getDefault(), "%.3f",
                        (gravity.xAccel*gravity.xAccel
                                + gravity.yAccel*gravity.yAccel
                                + gravity.zAccel*gravity.zAccel));
                return ret;
            }
            return null;
        }

    }



