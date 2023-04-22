package main.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TouchSensorImpl {
    private final TouchSensor touchSensor;

    public TouchSensorImpl(HardwareMap hardwareMap) {
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
    }

    public boolean isPressed() {
        return touchSensor.isPressed();
    }

    public double getPressedValue() {
        return touchSensor.getValue();
    }
}
