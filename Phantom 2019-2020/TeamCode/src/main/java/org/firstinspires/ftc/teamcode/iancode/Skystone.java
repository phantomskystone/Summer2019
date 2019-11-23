package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.RedAuto;

@Autonomous(name="Skystone (RED) [WIP]",group="autonomous")
public class Skystone extends RedAuto {

    MecanumConfig robot = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
      //  this.sideA="red";
        //super.d();

        ElapsedTime t  = new ElapsedTime();
        t.reset();
        robot.init(hardwareMap);
        robot.reverse();
        robot.twom.resetDeviceConfigurationForOpMode();
        //robot.imu.initialize(robot.parameters);
        MecanumRedux2 MecanumInstance = new MecanumRedux2(this);
        telemetry.addData("Status","driving!");
        telemetry.update();
        waitForStart();
        int angle = 45;

        MecanumInstance.middleDrive(robot,0,0.3f);

        while ((robot.twom.getDistance(DistanceUnit.INCH)>5&&!isStopRequested())){telemetry.addData("Status","waiting! "+robot.twom.getDistance(DistanceUnit.INCH));
            telemetry.update();sleep(25);}
        //we are now 3" away
        int iv = robot.cs.alpha();
        int lv = iv-2;
        int hv = iv+2;
        MecanumInstance.msDrive(robot,90,0.5f);
        while (robot.cs.alpha()>lv&&!isStopRequested()){ //<16 is dark >18 is light
            //dark spot
            telemetry.addData("Status","dark!");
            telemetry.update();
        }
        long nano = System.nanoTime();
        while (robot.cs.alpha()<hv&&!isStopRequested()){telemetry.addData("Status","waiting 2! cv: "+robot.cs.alpha()+" lv: "+lv+" hv: "+hv);
            telemetry.update();}
        telemetry.addData("Time",(int)t.seconds());
        long newNano = System.nanoTime()-nano;
        float time = (float)(newNano/1_000_000_000.0);
        MecanumInstance.sdrive(robot,-90,time/2,0.5f);



    }

}
