package main.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class RobotBase {
    public final OpMode opMode;

    public RobotBase(OpMode opMode) {
        this.opMode = opMode;
    }

    public abstract void initSubsystems();
    public abstract void run();
}
