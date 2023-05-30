package main.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public final class Time {
    private static final ElapsedTime time = new ElapsedTime();

    public static void wait(int seconds) {
        time.reset();
        while (time.time(TimeUnit.SECONDS) < seconds) {

        }
    }
}
