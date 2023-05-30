package main.auto;

import android.os.Build;

import androidx.annotation.RequiresApi;

import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;
import main.util.Time;

public class SquareDrivePathInstruction extends InstructionBase {
    private final MecanumHolonomicDriveAdvanced drive;

    public SquareDrivePathInstruction(MecanumHolonomicDriveAdvanced drive) {
        this.drive = drive;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void execute() {
        drive.driveStraight(20, MecanumHolonomicDriveAdvanced.DistanceUnit.CENTIMETRES);
        Time.wait(1);
        drive.strafeStraight(20, MecanumHolonomicDriveAdvanced.DistanceUnit.CENTIMETRES);
        Time.wait(1);
        drive.driveStraight(-20, MecanumHolonomicDriveAdvanced.DistanceUnit.CENTIMETRES);
        Time.wait(1);
        drive.strafeStraight(-20, MecanumHolonomicDriveAdvanced.DistanceUnit.CENTIMETRES);
    }
}
