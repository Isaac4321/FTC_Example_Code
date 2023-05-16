package main.hardware.sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MRColorSensorImpl {
    private final ModernRoboticsI2cColorSensor colorSensor;

    public MRColorSensorImpl(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "colorSensor");
    }

    public double getAlphaDetected() {
        return colorSensor.alpha();
    }
}
