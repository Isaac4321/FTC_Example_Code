package main.hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LimitSwitchImpl {
    private final DigitalChannel limitSwitch;

    public LimitSwitchImpl(HardwareMap hardwareMap) {
        limitSwitch = hardwareMap.get(DigitalChannel.class, "limitSwitch");
        limitSwitch.setMode(DigitalChannel.Mode.OUTPUT);
    }

    public boolean isPressed() {
        return limitSwitch.getState();
    }
}
