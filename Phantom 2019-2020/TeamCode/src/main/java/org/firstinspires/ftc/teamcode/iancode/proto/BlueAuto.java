package org.firstinspires.ftc.teamcode.iancode.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class BlueAuto extends LinearOpMode implements IAuto{
    //public String sideA="red";

    @Override
    public String getSide() {
        return "blue";
    }
}
