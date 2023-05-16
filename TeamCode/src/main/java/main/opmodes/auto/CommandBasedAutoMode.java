package main.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import main.commands.CommandScheduler;
import main.robot.Robot;

@Autonomous(name="CommandBasedAutoMode")
public class CommandBasedAutoMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (opModeIsActive()) {

        }
    }
}
