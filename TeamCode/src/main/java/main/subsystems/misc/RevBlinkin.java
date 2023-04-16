package main.subsystems.misc;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class RevBlinkin extends SubsystemBase {
    private final RevBlinkinLedDriver blinkin;

    public RevBlinkin(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        ServoControllerEx servoController = hardwareMap.get(ServoControllerEx.class, "blinkinServoController");
        blinkin = new RevBlinkinLedDriver(servoController, 0);
    }

    public void setColour(RevBlinkinLedDriver.BlinkinPattern pattern) {
        blinkin.setPattern(pattern);
    }
}
