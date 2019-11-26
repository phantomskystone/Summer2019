package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.BlueAuto;

@Autonomous(name="Skystone2 (BLUE) [WIP]",group="autonomous")
public class Skystone2Blue extends BlueAuto {

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
//        int angle = 45;

        MecanumInstance.middleDrive(robot,0,0.3f);

        while ((robot.twom.getDistance(DistanceUnit.INCH)>4&&!isStopRequested())){telemetry.addData("Status","waiting! "+robot.twom.getDistance(DistanceUnit.INCH));
            telemetry.update();sleep(25);}
        //we are now 3" away
        robot.stop();
        sleep(100);
       // int iv = robot.cs.alpha();
       // int lv = iv-3;
       // int hv = iv+16;
        //long nano = System.nanoTime();
        //MecanumInstance.msDrive(robot,90,0.5f);
        boolean firstStone=false;
        while (((robot.cs.argb()>0)) && !isStopRequested()){ //<16 is dark >18 is light
            //dark spot
            //while lighter than


            MecanumInstance.mIMUddleDrive(90,0.5f);
            telemetry.addData("Status","dark! cv: "+robot.cs.argb());
            telemetry.update();

        }
        //robot.stop();
        //while((!gamepad1.a)&&!isStopRequested()){telemetry.addData("Status",firstStone+" dark! cv: "+robot.cs.alpha()+" lv: "+lv+" hv: "+hv);
          //  telemetry.update();}
        //long newNano = System.nanoTime()-nano;
        //float time = (float)(newNano/1_000_000_000.0);





        //sleep(400);
        robot.stop();
        sleep(500);
        MecanumInstance.sdrive(robot,0,0.3f,0.25f);
        robot.skyServo.setPosition(0.9);
        sleep(500);
        MecanumInstance.sdrive(robot,0,1.5f,-0.25f);

       // MecanumInstance.sdrive(robot,0,0.15f,1f);
        MecanumInstance.sdrive(robot,0,0.8f,-0.25f);
        sleep(500);
        MecanumInstance.imuDrive(-90,3f,0.85f);
        sleep(100);
        robot.skyServo.setPosition(0);
        sleep(500);
        MecanumInstance.sdrive(robot,0,0.3f,-0.25f);
        MecanumInstance.imuDrive(90,3.8f,0.85f);

       // MecanumInstance.sdrive(robot,-90,5f,0.5f);
        robot.stop();
        MecanumInstance.middleDrive(robot,0,0.3f);
        while ((robot.twom.getDistance(DistanceUnit.INCH)>4&&!isStopRequested())){telemetry.addData("Status","waiting! "+robot.twom.getDistance(DistanceUnit.INCH));
            telemetry.update();sleep(25);}
        robot.stop();
        sleep(500);
        t.reset();
        boolean broken=false;
        while (((robot.cs.argb()>0)) && !isStopRequested()){ //<16 is dark >18 is light
            //dark spot
            //while lighter than

            if (t.seconds()>=3.25){
                broken=true;
                break;

            }
            MecanumInstance.mIMUddleDrive(90,0.5f);
            telemetry.addData("Status","dark! cv: "+robot.cs.argb());
            telemetry.addData("Time",t.seconds());
            telemetry.update();

        }
        if (!broken) {
            robot.stop();
            sleep(500);
            MecanumInstance.sdrive(robot, 0, 0.3f, 0.25f);
            robot.skyServo.setPosition(0.9);
            sleep(500);
            MecanumInstance.sdrive(robot, 0, 1.5f, -0.25f);

            // MecanumInstance.sdrive(robot,0,0.15f,1f);
            MecanumInstance.sdrive(robot, 0, 0.8f, -0.25f);
            sleep(500);
            MecanumInstance.imuDrive(-90, 4f, 0.85f);
            sleep(100);
            robot.skyServo.setPosition(0);
            MecanumInstance.imuDrive(90, 0.5f, 1);
        }else{
            MecanumInstance.sdrive(robot, 0, 2.5f, -0.25f);
            MecanumInstance.imuDrive(-90, 3f, 0.85f);
        }
       // sleep(500);
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
