package main.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorImpl {
    private final DistanceSensor distanceSensor;

    public DistanceSensorImpl(HardwareMap hardwareMap) {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
    }

    public double getDistance(DistanceUnit distanceUnit) {
        return distanceSensor.getDistance(distanceUnit);
    }
}
