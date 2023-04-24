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

    public AccelerationSensorImpl(HardwareMap hardwareMap) {
        accelerationSensor = hardwareMap.get(AccelerationSensor.class, "accelerationSensor");
    }

    public Acceleration getAcceleration() {
        return accelerationSensor.getAcceleration();
    }
}
