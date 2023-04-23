package main.hardware;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Rev Robotics Analog Pressure Sensor.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-11-1107/>Analog Pressure Sensor</a>
 */
public class PressureSensorImpl {
    private final AnalogSensor pressureSensor;
    private final int SCALED = 1;

    /** Initializes the pressure sensor, make sure to have the pressure sensor mapped in your configuration. */
    public PressureSensorImpl(HardwareMap hardwareMap) {
        pressureSensor = hardwareMap.get(AnalogSensor.class, "pressureSensor");
    }

    /** Return the scaled analog output from the pressure sensor. */
    public double getAnalogOutput() {
        return pressureSensor.readRawVoltage() * SCALED;
    }
}
