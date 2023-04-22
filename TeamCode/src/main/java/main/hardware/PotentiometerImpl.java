package main.hardware;

import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PotentiometerImpl {
    private final AnalogSensor potentiometer;
    private final int SCALED = 1;

    public PotentiometerImpl(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.get(AnalogSensor.class, "potentiometer");
    }

    public double getAnalogInput() {
        return potentiometer.readRawVoltage() * SCALED;
    }
}
