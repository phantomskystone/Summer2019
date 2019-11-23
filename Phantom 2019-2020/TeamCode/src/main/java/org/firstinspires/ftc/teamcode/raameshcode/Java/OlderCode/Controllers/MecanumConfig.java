package org.firstinspires.ftc.teamcode.raameshcode.Java.OlderCode.Controllers;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class   MecanumConfig {

    public DcMotor frontLeft  = null;
    public DcMotor backLeft   = null;
    public DcMotor frontRight = null;
    public DcMotor backRight  = null;
   public BNO055IMU imu      = null;
    public Servo armServo=null;
    public Servo capServo=null;
    public Servo stoneServo=null;
    HardwareMap hwMap = null;
    boolean newC = true;
    boolean IMU=false;
    public Acceleration Accel;
    public DistanceSensor sensorRange;

    public BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public Orientation             lastAngles = new Orientation();


    public double                  globalAngle;


    public MecanumConfig() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;


        sensorRange = hwMap.get(DistanceSensor.class, "DistanceSensor");



        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;



        boolean isNewChassis=true;
        try
        {
            hwMap.get(Servo.class, "newC");
        }catch (IllegalArgumentException ex){isNewChassis=false;}
        newC=isNewChassis;

        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        backRight = hwMap.get(DcMotor.class, "back_right_motor");

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        /** //Default New Chassis **/

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

/*
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        if (newC){
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
           // backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          //  backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
           // frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            //frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        }else{
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        }
*/

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backRight = hwMap.get(DcMotor.class, "back_right_motor");
        //try {hwMap.get(Servo.class, "newC"){
        //}
        //catch(IllegalArgumentException exce)
        armServo= hwMap.get(Servo.class,"armServo");
        armServo.setDirection(Servo.Direction.REVERSE);
        capServo= hwMap.get(Servo.class,"capServo");
        capServo.setDirection(Servo.Direction.REVERSE);
        stoneServo= hwMap.get(Servo.class,"stoneServo");
        stoneServo.setDirection(Servo.Direction.REVERSE);

    }

    public void doIMU(){
        parameters.mode =  BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
    public void reverse() {
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void forward() {
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
/*
    public void backReverse () {
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }
*/


}
