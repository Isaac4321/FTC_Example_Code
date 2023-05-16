package main.subsystems.custom;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.hardware.misc.RevBlinkinImpl;
import main.subsystems.SubsystemBase;
import main.subsystems.claw.SingleServoClaw;

/** This subsystem lets the driver know when the claw is opened or close. */
public class ClawLEDFeedback extends SubsystemBase {
    private final SingleServoClaw claw;
    private final RevBlinkinImpl blinkin;

    public ClawLEDFeedback(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        claw = new SingleServoClaw(telemetry, hardwareMap);
        blinkin = new RevBlinkinImpl(hardwareMap, 0);
    }

    public void openClaw() {
        claw.openClaw();
        blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.RED);
    }

    public void closeClaw() {
        claw.closeClaw();
        blinkin.setColour(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }
}
