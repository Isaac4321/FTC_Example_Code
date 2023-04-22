package main.hardware;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

/**
 * Example subsystem for controlling the REVBlinkin.
 */
public class RevBlinkinImpl {
    private final RevBlinkinLedDriver blinkin;

    public RevBlinkinImpl(HardwareMap hardwareMap) {
        ServoControllerEx servoController = hardwareMap.get(ServoControllerEx.class, "blinkinServoController");
        blinkin = new RevBlinkinLedDriver(servoController, 0); // Port: look on control/expansion hub for port #.
    }

    /** Sets the LEDs to a specific pattern. A few example patterns are as follows:
     *  - RED: Solid colour red
     *  - LIME: Solid colour lime
     *  - CONFETTI: Rainbow fun-ness.
     */
    public void setColour(RevBlinkinLedDriver.BlinkinPattern pattern) {
        blinkin.setPattern(pattern);
    }
}
