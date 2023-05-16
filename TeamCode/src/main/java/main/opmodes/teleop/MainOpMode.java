package main.opmodes.teleop;

import android.os.Build;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import main.robot.Robot;

/**
 * A basic tele-op mode for running the robot.
 */
@TeleOp(name="Competition-Ready Op Mode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            robot = new Robot(this);
        }

        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            robot.run();
        }

    }
}
