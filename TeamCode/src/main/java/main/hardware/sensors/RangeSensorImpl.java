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

    public RangeSensorImpl(HardwareMap hardwareMap) {
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");
    }

    public double getDistance(DistanceUnit distanceUnit) {
        return rangeSensor.getDistance(distanceUnit);
    }

    public double getUltrasonic() {
        return rangeSensor.rawUltrasonic();
    }

    public double getOptical() {
        return rangeSensor.rawOptical();
    }
}
