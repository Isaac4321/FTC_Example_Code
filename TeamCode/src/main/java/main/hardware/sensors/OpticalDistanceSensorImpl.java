package main.hardware.sensors;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * An example implementation of the Modern Robotics Optical Distance Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/optical-distance-sensor/>Optical Distance Sensor</a>
 */
public class OpticalDistanceSensorImpl {
    private final OpticalDistanceSensor opticalDistanceSensor;

    /** Initializes the Optical sensor, make sure to have the optical sensor mapped in your configuration. */
    public OpticalDistanceSensorImpl(HardwareMap hardwareMap) {
        opticalDistanceSensor = hardwareMap.get(OpticalDistanceSensor.class, "opticalDistanceSensor");
    }

    /** Returns the light level detected by the light sensor. [0.0, 1.0] */
    public double getLight() {
        return opticalDistanceSensor.getLightDetected();
    }
}
