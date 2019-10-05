package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MecanumConfig {

    public DcMotor frontLeft  = null;
    public DcMotor backLeft   = null;
    public DcMotor frontRight = null;
    public DcMotor backRight  = null;
   public BNO055IMU imu      = null;
    HardwareMap hwMap = null;

    public BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public Orientation             lastAngles = new Orientation();


    public double                  globalAngle;


    public MecanumConfig() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        parameters.mode =  BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;


        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        backRight = hwMap.get(DcMotor.class, "back_right_motor");
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
