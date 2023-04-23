package main.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * An example implementation of the Rev Robotics Distance Sensor 2m.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-31-1505/>Distance Sensor 2m</a>
 */
public class DistanceSensorImpl {
    private final DistanceSensor distanceSensor;

    /** Initializes the distance sensor, make sure to have the distance sensor mapped in your configuration. */
    public DistanceSensorImpl(HardwareMap hardwareMap) {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
    }

    /** Return the distance the sensor currently reads, the distance is converted based on the distanceUnit. */
    public double getDistance(DistanceUnit distanceUnit) {
        return distanceSensor.getDistance(distanceUnit);
    }
}
