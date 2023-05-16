package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;

/**
 * An example implementation of the Modern Robotics Acceleration Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/compass-acceleration-sensor/>Acceleration Sensor</a>
 */
public class AccelerationSensorImpl {
    private final AccelerationSensor accelerationSensor;

    /** Initializes the Acceleration sensor, make sure to have the acceleration sensor mapped in your configuration. */
    public AccelerationSensorImpl(HardwareMap hardwareMap) {
        accelerationSensor = hardwareMap.get(AccelerationSensor.class, "accelerationSensor");
    }

    /** Returns the acceleration of the sensor in g's*/
    public Acceleration getAcceleration() {
        return accelerationSensor.getAcceleration();
    }
}
