package org.firstinspires.ftc.teamcode.iancode.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class RedAuto extends LinearOpMode implements IAuto{
    //public String sideA="red";

    @Override
    public String getSide() {
        return "red";
    }
}
