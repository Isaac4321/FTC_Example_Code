package main.hardware;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Rev Robotics Potentiometer.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-31-1155/>Potentiometer</a>
 */
public class PotentiometerImpl {
    private final AnalogSensor potentiometer;
    private final int SCALED = 1;

    /** Initializes the potentiometer, make sure to have the potentiometer mapped in your configuration. */
    public PotentiometerImpl(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.get(AnalogSensor.class, "potentiometer");
    }

    /** Return the scaled analog output from the potentiometer. */
    public double getAnalogOutput() {
        return potentiometer.readRawVoltage() * SCALED;
    }
}
