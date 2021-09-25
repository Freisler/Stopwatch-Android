package com.freisler_stopky.Model;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.Locale;
import java.util.Observable;

public class Timer extends Observable {
    private final Handler handler;
    private boolean running;
    private long start_time, time_in_ms, time_swap_buff, update_time = 0L;
    private String time;

    public Timer() {
        running = false;
        handler = new Handler(Looper.getMainLooper());
    }

    Runnable timer_thread = new Runnable() {
        @Override
        public void run() {
            if(running) {
                time_in_ms = SystemClock.uptimeMillis() - start_time;
                update_time = time_swap_buff + time_in_ms;

                int msec = (int) (update_time % 1000) / 10;
                int sec = (int) update_time / 1000;
                int min = sec / 60;
                if(sec > 59)
                    sec = sec % 60;

                time = String.format(Locale.getDefault(), "%02d:%02d.%02d", min, sec, msec);
                setChanged();
                notifyObservers();

                handler.postDelayed(this, 0);
            }
        }
    };

    public boolean getTimerStatus() {
        return running;
    }

    public void resetTimer() {
        running = false;
        start_time = 0L;
        time_in_ms = 0L;
        time_swap_buff = 0L;
        update_time = 0L;
    }

    public void runTimer() {
        if(!running) {
            running = true;
            start_time = SystemClock.uptimeMillis();
            handler.postDelayed(timer_thread, 0);
        } else {
            running = false;
            time_swap_buff += time_in_ms;
            handler.removeCallbacks(timer_thread);
        }
    }

    public String setTime() {
        return time;
    }
}
