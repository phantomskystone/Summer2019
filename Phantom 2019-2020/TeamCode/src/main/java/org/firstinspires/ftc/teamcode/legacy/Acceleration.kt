package org.firstinspires.ftc.teamcode.legacy

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime


@Autonomous(name = "Acceleration", group = "autonomous")
class Acceleration : LinearOpMode() {


    internal var robot = PhantomConfig()

    private val runtime = ElapsedTime()


    internal var leftPosition: Int = 0
    internal var rightPosition: Int = 0
    internal var turn: Double = 0.toDouble()

    internal var gold = true


    override fun runOpMode() {

        robot.init(hardwareMap)

        waitForStart()

        runtime.reset()

        accelDrive(true, 0.0, 1.0, 30.0, 30.0, 30.0)


    }

    private fun accelDrive(UPaccel: Boolean, startSpeed: Double, endSpeed: Double, leftInches: Double, rightInches: Double, timoutS: Double) {

        runtime.reset()

        if (leftInches > rightInches) {

            var A_currentSpeed = startSpeed
            val S_increment = Math.abs(endSpeed - startSpeed) / leftInches
            var A_driveInches = 0.0

            while (leftInches != A_driveInches) {
                if (UPaccel) {
                    A_currentSpeed = A_currentSpeed + S_increment
                }
                if (!UPaccel) {
                    A_currentSpeed = A_currentSpeed - S_increment
                }
                encoderDrive(A_currentSpeed + 0, leftInches / 100, rightInches / 100, 10.0)
                A_driveInches = A_driveInches + leftInches / 100
                A_driveInches = A_driveInches + 1
                if (timoutS >= runtime.seconds()) {
                    robot.leftDrive.power = 0.0
                    robot.rightDrive.power = 0.0
                    break
                }
            }

        } else {
            if (leftInches < rightInches) {
                var A_currentSpeed = startSpeed
                val S_increment = Math.abs(endSpeed - startSpeed) / rightInches
                var A_driveInches = 0.0

                while (rightInches != A_driveInches) {
                    if (UPaccel) {
                        A_currentSpeed = A_currentSpeed + S_increment
                    }
                    if (!UPaccel) {
                        A_currentSpeed = A_currentSpeed - S_increment
                    }
                    encoderDrive(A_currentSpeed + 0, leftInches / 100, rightInches / 100, 10.0)
                    A_driveInches = A_driveInches + rightInches / 100
                    A_driveInches = A_driveInches + 1
                    if (timoutS >= runtime.seconds()) {
                        robot.leftDrive.power = 0.0
                        robot.rightDrive.power = 0.0
                        break
                    }
                }
            } else {
                if (leftInches == rightInches) {
                    var A_currentSpeed = startSpeed
                    val S_increment = Math.abs(endSpeed - startSpeed) / leftInches
                    var A_driveInches = 0.0

                    while (leftInches != A_driveInches) {
                        if (UPaccel) {
                            A_currentSpeed = A_currentSpeed + S_increment
                        }
                        if (!UPaccel) {
                            A_currentSpeed = A_currentSpeed - S_increment
                        }
                        encoderDrive(A_currentSpeed + 0, leftInches / 100, rightInches / 100, 10.0)
                        A_driveInches = A_driveInches + leftInches / 100
                        A_driveInches = A_driveInches + 1
                        if (timoutS >= runtime.seconds()) {
                            robot.leftDrive.power = 0.0
                            robot.rightDrive.power = 0.0
                            break
                        }
                    }
                }
            }
        }

    }

    private fun encoderDrive(speed: Double,
                             leftInches: Double, rightInches: Double,
                             timeoutS: Double) {
        val newLeftTarget: Int
        val newRightTarget: Int

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftDrive.currentPosition + (leftInches * COUNTS_PER_INCH).toInt()
            newRightTarget = robot.rightDrive.currentPosition + (rightInches * COUNTS_PER_INCH).toInt()
            robot.leftDrive.targetPosition = newLeftTarget
            robot.rightDrive.targetPosition = newRightTarget

            // Turn On RUN_TO_POSITION
            robot.leftDrive.mode = DcMotor.RunMode.RUN_TO_POSITION
            robot.rightDrive.mode = DcMotor.RunMode.RUN_TO_POSITION

            // reset the timeout time and start motion.
            runtime.reset()
            robot.leftDrive.power = Math.abs(speed)
            robot.rightDrive.power = Math.abs(speed)

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    (robot.leftDrive.isBusy || robot.rightDrive.isBusy)) {

                /*    // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
                */
            }

            // Stop all motion;
            robot.leftDrive.power = 0.0
            robot.rightDrive.power = 0.0

            // Turn off RUN_TO_POSITION
            robot.leftDrive.mode = DcMotor.RunMode.RUN_USING_ENCODER
            robot.rightDrive.mode = DcMotor.RunMode.RUN_USING_ENCODER

            //  sleep(250);   // optional pause after each move
        }
    }

    companion object {

        internal val COUNTS_PER_MOTOR_REV = 1440.0    // eg: TETRIX Motor Encoder
        internal val DRIVE_GEAR_REDUCTION = 1.0     // This is < 1.0 if geared UP
        internal val WHEEL_DIAMETER_INCHES = 4.0     // For figuring circumference
        internal val COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / (WHEEL_DIAMETER_INCHES * 3.141592653589793238)

        internal val TURN_DIAMETER = 14.25
        internal val FULL_TURN = TURN_DIAMETER * 3.1415926535897932384626

        internal val FORWARD_SPEED = 0.50   //The speed we use to go forward.
        internal val TURN_SPEED = 0.15
    }
}
