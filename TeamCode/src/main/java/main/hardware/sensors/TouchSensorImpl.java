package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * An example implementation of the Rev Robotics Touch Sensor.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-31-1425/>Touch Sensor</a>
 */
public class TouchSensorImpl {
    private final TouchSensor touchSensor;

    /** Initializes the touch sensor, make sure to have the touch sensor mapped in your robot configuration. */
    public TouchSensorImpl(HardwareMap hardwareMap) {
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
    }

    /** Return whether or not the touch sensor has been pressed. */
    public boolean isPressed() {
        return touchSensor.isPressed();
    }

    /** Return a double representing how hard the touch sensor was pressed, this is not always available. */
    public double getPressedValue() {
        return touchSensor.getValue();
    }
}
