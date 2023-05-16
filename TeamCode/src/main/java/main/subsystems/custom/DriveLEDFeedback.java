package main.subsystems.custom;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.hardware.misc.RevBlinkinImpl;
import main.subsystems.SubsystemBase;
import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveBasic;

/**
 * This subsystem changes the LEDs based on how much power is being given to the drive motors.
 */
public class DriveLEDFeedback extends SubsystemBase {
    private final RevBlinkinImpl blinkin;
    private final MecanumHolonomicDriveBasic drive;

    public DriveLEDFeedback(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        blinkin = new RevBlinkinImpl(hardwareMap, 0);
        drive = new MecanumHolonomicDriveBasic(telemetry, hardwareMap);
        blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.GRAY);
    }

    public void drive(double forward, double strafe, double turn, boolean scaled) {
        if (forward <= 0.3 || strafe <= 0.3 || turn <= 0.3) {
            blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
        }
        else if (forward <= 0.6 || strafe <= 0.6 || turn <= 0.6) {
            blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
        }
        else {
            blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.FIRE_LARGE);
        }
        drive.drive(forward, strafe, turn, scaled);
    }
}
