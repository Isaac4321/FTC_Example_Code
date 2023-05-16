package main.subsystems.misc;

import android.content.Context;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class SoundSubsystem extends SubsystemBase {
    private final int boomID;
    private final int crashID;
    private final int quackID;

    private final Context appContext;

    public SoundSubsystem(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        boomID = hardwareMap.appContext.getResources().getIdentifier("boom", "res", hardwareMap.appContext.getPackageName());
        crashID = hardwareMap.appContext.getResources().getIdentifier("crash", "res", hardwareMap.appContext.getPackageName());
        quackID = hardwareMap.appContext.getResources().getIdentifier("quack", "res", hardwareMap.appContext.getPackageName());
        appContext = hardwareMap.appContext;
    }

    public void play(SoundFile file) {
        switch (file) {
            case BOOM:
                SoundPlayer.getInstance().startPlaying(appContext, boomID);
                break;
            case CRASH:
                SoundPlayer.getInstance().startPlaying(appContext, crashID);
                break;
            case QUACK:
                SoundPlayer.getInstance().startPlaying(appContext, quackID);
                break;
            default:
                SoundPlayer.getInstance().stopPlayingAll();
        }
    }

    public void stop() {
        SoundPlayer.getInstance().stopPlayingAll();
    }

    enum SoundFile {
        BOOM,
        CRASH,
        QUACK
    }
}
