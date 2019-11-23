package org.firstinspires.ftc.teamcode.raameshcode.Java.NewerCode.SeasonCode.Controllers;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers.MecanumConfig;

public class fieldLocator implements Runnable {
    public double initRobotXOffset = 0;
    public double initRobotYOffset = 0;
    double WorldRobotx = initRobotXOffset;
    double WorldRoboty = initRobotYOffset;
    MecanumConfig robot  = new MecanumConfig();
    LinearOpMode OpMode  = new LinearOpMode() {public void runOpMode() throws InterruptedException{}};
    ElapsedTime DistTime = new ElapsedTime();
    double initVeloc = 0;
    double accelX;
    double accelY;
    public boolean isThreadStopRequested = false;
    public boolean isObjectDetected = false;
    public double objectBasedPosX = 0;
    public double objectBasedPosY = 0;
    @Override
    public void run() {
        isThreadStopRequested = false;
        DistTime.reset();
        robot.parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        accelX = robot.Accel.xAccel * 39.37008;
        accelY = robot.Accel.yAccel * 39.37008;
        while (!isThreadStopRequested && !OpMode.isStopRequested()) {
            if (isObjectDetected) {
                WorldRobotx = objectBasedPosX;
                WorldRoboty = objectBasedPosY;
            }else {
                WorldRobotx += (initVeloc*DistTime.seconds()) + (0.5*accelX*DistTime.seconds()*DistTime.seconds());
                WorldRoboty += (initVeloc*DistTime.seconds()) + (0.5*accelY*DistTime.seconds()*DistTime.seconds());
            }
        }
    }
}
