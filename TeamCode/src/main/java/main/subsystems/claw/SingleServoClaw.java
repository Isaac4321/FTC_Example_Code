package main.subsystems.claw;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

/**
 * An example subsystem of a claw driven by one servo.
 */
public class SingleServoClaw extends SubsystemBase {
    private static final class Constants {
        private static final double CLOSED_CLAW_POS = 0.0;
        private static final double OPENED_CLAW_POS = 1.0;
    }

    private final Servo clawServo;

    /** Initializes the servo, make sure to have the servo mapped in your robot configuration. */
    public SingleServoClaw(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        clawServo.setDirection(Servo.Direction.FORWARD);
    }

    /** Closes the claw. */
    public void closeClaw() {
        clawServo.setPosition(Constants.CLOSED_CLAW_POS);
    }

    /** Opens the claw. */
    public void openClaw() {
        clawServo.setPosition(Constants.OPENED_CLAW_POS);
    }
}
