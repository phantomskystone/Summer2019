package org.firstinspires.ftc.teamcode.iancode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.IAuto;
import org.firstinspires.ftc.teamcode.iancode.proto.RedAuto;

@Autonomous(name="Bridge (RED)",group="autonomous")
public class Bridge extends RedAuto {

    MecanumConfig robot = new MecanumConfig();
    @Override
    public void runOpMode() throws InterruptedException {
      //  this.sideA="red";
        super.d();

        robot.init(hardwareMap);
        robot.reverse();
        MecanumRedux2 MecanumInstance = new MecanumRedux2(this);
        waitForStart();
        MecanumInstance.sdrive(robot,0,0.2f,0.3f);
        MecanumInstance.sdrive(robot,-90,1.2f,0.5f);
        MecanumInstance.sdrive(robot,0,1.8f,0.5f);
        MecanumInstance.sdrive(robot,0,0.533333333f,0.3f);
        robot.armServo.setPosition(1);
        sleep(3000);
        MecanumInstance.sdrive(robot,0,3f,-0.5f);
        sleep(1000);
        robot.armServo.setPosition((20/180));
        sleep(3000);
        MecanumInstance.sdrive(robot,90,3.2f,0.5f);
        MecanumInstance.sdrive(robot,0,1.2f,0.5f);
        MecanumInstance.sdrive(robot,-90,2f,0.5f);
        MecanumInstance.sdrive(robot,80,3f,0.5f);
        
    }

}
