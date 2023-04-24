package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Modern Robotics Magnetic Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/integrating-gyro/>Magnetic Sensor</a>
 */
public class MagneticSensorImpl {
    private final AnalogSensor magneticSensor;

    public MagneticSensorImpl(HardwareMap hardwareMap) {
        magneticSensor = hardwareMap.get(AnalogSensor.class, "magneticSensor");
    }

    public double getMagnetism() {
        return magneticSensor.readRawVoltage();
    }
}
