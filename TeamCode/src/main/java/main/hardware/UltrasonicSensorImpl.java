package main.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class UltrasonicSensorImpl {
    private final UltrasonicSensor ultrasonicSensor;

    public UltrasonicSensorImpl(HardwareMap hardwareMap) {
        ultrasonicSensor = hardwareMap.get(UltrasonicSensor.class, "ultrasonicSensor");
    }

    public double getUltrasonicLevel() {
        return ultrasonicSensor.getUltrasonicLevel();
    }
}
