package org.firstinspires.ftc.teamcode.legacy;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

public class config {

    public DcMotor leftDrive  = null;
    public DcMotor rightDrive = null;

    HardwareMap hwMap = null;

    private ElapsedTime timer = new ElapsedTime();

    public config(){

    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");

        leftDrive .setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive .setPower(0);
        rightDrive.setPower(0);

        leftDrive .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



}
