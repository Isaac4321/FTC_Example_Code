package main.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class SubsystemBase {
    private final Telemetry telemetry;
    private final HardwareMap hardwareMap;

    protected SubsystemBase(Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
    }
}
