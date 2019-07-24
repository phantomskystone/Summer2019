package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


public class PhantomConfig {
    
    public DcMotor rightDrive = null;
    public DcMotor leftDrive = null;
    public DcMotor climbMotor = null;
    public DcMotor armMotor = null;
    public Servo markerServo  = null;
    public Servo intakeServo  = null;
    public Servo slideServo   = null;
    public BNO055IMU gyro =     null;
    public DistanceSensor distanceSensor = null;
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    
    
    public PhantomConfig(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        leftDrive = hwMap.get(DcMotor.class, "left_drive");
        climbMotor = hwMap.get(DcMotor.class, "climb_motor");
        armMotor = hwMap.get(DcMotor.class, "arm_motor");

        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        climbMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        climbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        climbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define and initialize ALL installed servos.
        markerServo = hwMap.get(Servo.class, "marker_servo");
        intakeServo = hwMap.get(Servo.class, "intake_servo");
        slideServo = hwMap.get(Servo.class, "slide_servo");

        gyro = hwMap.get(BNO055IMU.class, "imu");

        distanceSensor = hwMap.get(DistanceSensor.class, "distance_sensor");

    }


}
