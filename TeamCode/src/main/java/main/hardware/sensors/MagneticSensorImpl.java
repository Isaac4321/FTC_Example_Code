package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Modern Robotics Magnetic Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/integrating-gyro/>Magnetic Sensor</a>
 */
public class MagneticSensorImpl {
    private final AnalogSensor magneticSensor;
    private final int SCALED = 1;

    /** Initializes the Magnetic sensor, make sure to have the magnetic sensor mapped in your robot configuration. */
    public MagneticSensorImpl(HardwareMap hardwareMap) {
        magneticSensor = hardwareMap.get(AnalogSensor.class, "magneticSensor");
    }

    /** Returns the current magnetic field. */
    public double getMagnetism() {
        return magneticSensor.readRawVoltage() * SCALED;
    }
}
