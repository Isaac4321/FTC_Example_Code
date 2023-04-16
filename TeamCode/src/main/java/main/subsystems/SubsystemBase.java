package main.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class SubsystemBase {
    private final Telemetry telemetry;
    private final HardwareMap hardwareMap;

    public SubsystemBase(Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
    }
}
