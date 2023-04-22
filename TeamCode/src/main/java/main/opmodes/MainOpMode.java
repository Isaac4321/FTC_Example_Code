package main.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import main.robot.Robot;

@TeleOp(name="Tele Op: Main Op Mode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            robot.run();
        }

    }
}
