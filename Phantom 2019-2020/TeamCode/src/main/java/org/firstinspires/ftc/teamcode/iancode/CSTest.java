package org.firstinspires.ftc.teamcode.iancode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumConfig;
import org.firstinspires.ftc.teamcode.iancode.proto.RedAuto;
@Autonomous(name="CSTest",group="autonomous")
public class CSTest extends RedAuto {
    MecanumConfig robot = new MecanumConfig();

    @Override
    public void runOpMode() throws InterruptedException {
        super.d();
        robot.init(hardwareMap);
        robot.reverse();

        MecanumRedux2 MecanumInstance = new MecanumRedux2(this);
        waitForStart();
        while (!isStopRequested()){
            telemetry.addData("Alpha",robot.cs.alpha());
            telemetry.addData("Hue",robot.cs.argb());
            telemetry.addData("Distance",robot.ds.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
    }
}
