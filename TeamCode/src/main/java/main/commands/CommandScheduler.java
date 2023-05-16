package main.commands;

import android.os.Build;

import java.util.ArrayList;

import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;

public abstract class CommandScheduler {
    private final static ArrayList<Command> commands = new ArrayList<>();

    public static void schedule(Command command) {
        commands.add(command);
    }

    public static void run() {
        for (Command command : commands) {
            command.run();
        }
    }
}

interface Command {
    void run();
}

abstract class CommandBase implements Command {

}

class DriveCommand extends CommandBase {
    private final MecanumHolonomicDriveAdvanced subsystem;

    public DriveCommand(MecanumHolonomicDriveAdvanced subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void run() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            subsystem.driveStraight(20, MecanumHolonomicDriveAdvanced.DistanceUnit.CENTIMETRES);
        }
    }
}