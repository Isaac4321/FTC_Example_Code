package main.hardware.sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * An example implementation of the Modern Robotics Range Sensor.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/range-sensor/>Range Sensor</a>
 */
public class RangeSensorImpl {
    private final ModernRoboticsI2cRangeSensor rangeSensor;

    /** Initializes the Range sensor, make sure to have the range sensor mapped in your robot configuration. */
    public RangeSensorImpl(HardwareMap hardwareMap) {
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");
    }

    /** Returns the distance read by the sensor, the distance unit is specified the the parameters. */
    public double getDistance(DistanceUnit distanceUnit) {
        return rangeSensor.getDistance(distanceUnit);
    }

    /** Returns the ultrasonic level read by the sensor. */
    public double getUltrasonic() {
        return rangeSensor.rawUltrasonic();
    }

    /** Returns the optical level read by the sensor. */
    public double getOptical() {
        return rangeSensor.rawOptical();
    }
}
