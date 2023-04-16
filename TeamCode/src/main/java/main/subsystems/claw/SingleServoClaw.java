package main.subsystems.claw;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class SingleServoClaw extends SubsystemBase {
    private static final class Constants {
        private static final double CLOSED_CLAW_POS = 0.0;
        private static final double OPENED_CLAW_POS = 1.0;
    }
    private final Servo clawServo;

    public SingleServoClaw(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        clawServo.setDirection(Servo.Direction.FORWARD);
    }

    public void closeClaw() {
        clawServo.setPosition(Constants.CLOSED_CLAW_POS);
    }

    public void openClaw() {
        clawServo.setPosition(Constants.OPENED_CLAW_POS);
    }
}
