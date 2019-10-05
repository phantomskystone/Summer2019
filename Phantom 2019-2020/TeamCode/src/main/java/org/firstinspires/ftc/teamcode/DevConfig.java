package org.firstinspires.ftc.teamcode;

import android.graphics.drawable.GradientDrawable;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;


public class DevConfig {

    public ElapsedTime     runtime = new ElapsedTime();

    public final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    public final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    public final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    public final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.141592653589793238);

    public final double TURN_DIAMETER               = 14.25;
    public final double FULL_TURN                   = TURN_DIAMETER*3.1415926535897932384626;

    public final double     FORWARD_SPEED             = 0.50;
    public final double     TURN_SPEED              = 0.15;


    public boolean stop = false;

    //ALL hardware

    public DcMotor rightDrive = null;
    public DcMotor leftDrive  = null;
    public DcMotor climbMotor = null;
    public DcMotor armMotor   = null;
    public Servo markerServo  = null;
    public Servo intakeServo  = null;
    public Servo slideServo   = null;
    public BNO055IMU imu      = null;
    public DistanceSensor distanceSensor = null;
    public DigitalChannel touchSensor = null;
    public ColorSensor colorSensor;
    public DistanceSensor color_distanceSensor;
    HardwareMap hwMap         = null;
    private ElapsedTime period  = new ElapsedTime();

    //ALL senors's parameters
    Orientation angles;
    Acceleration totalAcceleration; //remember that this also measures gravity
    public BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    Orientation             lastAngles = new Orientation();
    double                  globalAngle, power = .30, correction;
    boolean                 aButton, bButton, touched;

    public DevConfig(){

    }



    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

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

        // Define and intialize ALL installed sensors.
        distanceSensor = hwMap.get(DistanceSensor.class, "distance_sensor");
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        colorSensor = hwMap.get(ColorSensor.class, "color_sensor");
        color_distanceSensor = hwMap.get(DistanceSensor.class, "colorDistance_sensor");




    } //init

    public void encoderDrive(double speed, double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Determine new target position, and pass to motor controller
        newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRightTarget = rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        leftDrive.setTargetPosition(newLeftTarget);
        rightDrive.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        leftDrive.setPower(Math.abs(speed));
        rightDrive.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while ((!stop && (runtime.seconds() < timeoutS)) &&
                (leftDrive.isBusy() || rightDrive.isBusy())) {

            /*    // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
                */
        }

        // Stop all motion;
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // Turn off RUN_TO_POSITION
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // optional pause after each move

    } // EncoderDrive

    public void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    public double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }
    public void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        // rotate until turn is completed.
       if (!stop) {
           if (degrees < 0) {
               // On right turn we have to get off zero first.
               while (!stop && (getAngle() == 0)) {
               }

               while (!stop && (getAngle() > degrees)) {
               }
           } else    // left turn.
               while (!stop && (getAngle() < degrees)) {
               }

           // turn the motors off.
           rightDrive.setPower(0);
           leftDrive.setPower(0);
       }
        // wait for rotation to stop.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        // reset angle tracking on new heading.
        resetAngle();
    }
}
