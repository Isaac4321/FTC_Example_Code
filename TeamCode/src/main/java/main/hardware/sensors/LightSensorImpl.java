package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LightSensorImpl {
    private final AnalogSensor lightSensor;

    /** Initializes the Light sensor, make sure to have the light sensor mapped in your robot configuration. */
    public LightSensorImpl(HardwareMap hardwareMap) {
        lightSensor = hardwareMap.get(AnalogSensor.class, "magneticSensor");
    }

    public double getLightLevel() {
        return lightSensor.readRawVoltage();
    }
}
