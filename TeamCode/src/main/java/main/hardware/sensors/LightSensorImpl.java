package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Modern Robotics Light Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/light-sensor/>Light Sensor</a>
 */
public class LightSensorImpl {
    private final AnalogSensor lightSensor;

    public LightSensorImpl(HardwareMap hardwareMap) {
        lightSensor = hardwareMap.get(AnalogSensor.class, "magneticSensor");
    }

    public double getLightLevel() {
        return lightSensor.readRawVoltage();
    }
}
