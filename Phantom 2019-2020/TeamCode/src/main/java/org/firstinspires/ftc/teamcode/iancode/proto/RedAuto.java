package org.firstinspires.ftc.teamcode.iancode.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class RedAuto extends LinearOpMode implements IAuto{
    //public String sideA="red";
    //Right now these are useless, but more may be required in the future.
    //TODO
    @Override
    public String getSide() {
        return "red";
    }
}
