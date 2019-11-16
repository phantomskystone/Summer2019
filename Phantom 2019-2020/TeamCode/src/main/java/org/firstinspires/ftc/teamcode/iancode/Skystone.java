package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.RedAuto;

@Autonomous(name="Skystone (RED) [WIP]",group="autonomous")
public class Skystone extends RedAuto {

    MecanumConfig robot = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
      //  this.sideA="red";
        super.d();

        robot.init(hardwareMap);
        robot.reverse();
        //robot.imu.initialize(robot.parameters);
        MecanumRedux2 MecanumInstance = new MecanumRedux2(this);
        waitForStart();
        MecanumInstance.middleDrive(robot,0,0.33f);
        while (robot.ds.getDistance(DistanceUnit.INCH)>3&&isStopRequested()){}
        //we are now 1" away
        MecanumInstance.msDrive(robot,-90,0.5f);
        while (robot.cs.alpha()>16&&isStopRequested()){ //<16 is dark >18 is light
            //dark spot
        }
        long nano = System.nanoTime();
        while (robot.cs.alpha()<18&&isStopRequested());
        long newNano = System.nanoTime()-nano;
        float time = (float)(newNano/1_000_000_000.0);
        MecanumInstance.sdrive(robot,90,time,0.5f);
        //onit

    }

}
