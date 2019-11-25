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
        super.d();

        robot.init(hardwareMap);
        robot.doIMU();
        IMURedux MecanumInstance = new IMURedux(this,robot,0.025f);
        MecanumInstance.calibrate();
        robot.reverse();
        robot.doNewC();
        //robot.backReverse();
        ElapsedTime t  = new ElapsedTime();

        robot.twom.resetDeviceConfigurationForOpMode();
        robot.cs.enableLed(false);
        //robot.imu.initialize(robot.parameters);
        //MecanumRedux2 MecanumInstance = new MecanumRedux2(this);
        telemetry.addData("Status","driving!");
        telemetry.update();
        waitForStart();
        int angle = 45;

        MecanumInstance.middleDrive(robot,0,0.3f);

        while ((robot.twom.getDistance(DistanceUnit.INCH)>6.5&&!isStopRequested())){telemetry.addData("Status","waiting! "+robot.twom.getDistance(DistanceUnit.INCH));
            telemetry.update();sleep(25);}
        //we are now 3" away
        robot.stop();
        sleep(100);
        int iv = robot.cs.alpha();
        int lv = iv-3;
        int hv = iv+16;
        //long nano = System.nanoTime();
        //MecanumInstance.msDrive(robot,90,0.5f);
        boolean firstStone=false;
        while (((robot.cs.alpha()>lv)) && !isStopRequested()){ //<16 is dark >18 is light
            //dark spot
            //while lighter than


            MecanumInstance.mIMUddleDrive(90,0.5f);
            telemetry.addData("Status","dark! cv: "+robot.cs.alpha()+" lv: "+lv+" hv: "+hv);
            telemetry.update();
            if (robot.cs.alpha()>hv){
                //real
                firstStone=true;
                break;
            }
        }
        robot.stop();
        while((!gamepad1.a)&&!isStopRequested()){telemetry.addData("Status",firstStone+" dark! cv: "+robot.cs.alpha()+" lv: "+lv+" hv: "+hv);
            telemetry.update();}
        //long newNano = System.nanoTime()-nano;
        //float time = (float)(newNano/1_000_000_000.0);
        if (firstStone){
            iv = robot.cs.alpha();
            lv = iv-3;
            while (((robot.cs.alpha()>lv)) && !isStopRequested()){ //<16 is dark >18 is light
                //dark spot
                //while lighter than


                MecanumInstance.mIMUddleDrive(-90,0.5f);
                telemetry.addData("Status","dark!2 cv: "+robot.cs.alpha()+" lv: "+lv);
                telemetry.update();
            }

        }


        //sleep(400);
        robot.stop();
        sleep(2000);
        robot.skyServo.setPosition(0.9);
        sleep(2000);
        MecanumInstance.sdrive(robot,0,1.5f,-0.25f);
        sleep(2000);
        MecanumInstance.imuDrive(-90,5f,0.85f);
       // MecanumInstance.sdrive(robot,-90,5f,0.5f);
        robot.stop();
        /*
        long nano = System.nanoTime();
        while (robot.cs.alpha()<hv&&!isStopRequested()){telemetry.addData("Status","waiting 2! cv: "+robot.cs.alpha()+" lv: "+lv+" hv: "+hv);
            telemetry.update();}
        telemetry.addData("Time",(int)t.seconds());
        long newNano = System.nanoTime()-nano;
        float time = (float)(newNano/1_000_000_000.0);
        MecanumInstance.sdrive(robot,-90,time/2,0.5f);

         */



    }

}
