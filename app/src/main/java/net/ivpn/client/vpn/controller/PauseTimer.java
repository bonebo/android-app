package net.ivpn.client.vpn.controller;

import android.os.CountDownTimer;

class PauseTimer {
    private static final long TICK = 1000L;

    private CountDownTimer timer;
    private PauseTimerListener listener;
    private long millisUntilFinished;

    PauseTimer(PauseTimerListener listener) {
        this.listener = listener;
    }

    void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    void startTimer(long pauseDuration) {
        if (timer != null) {
            timer.cancel();
        }
        timer = getTimer(pauseDuration);
        timer.start();
    }

    long getMillisUntilFinished() {
        return millisUntilFinished;
    }

    private CountDownTimer getTimer(long pauseTime) {
        return new CountDownTimer(pauseTime, TICK) {
            @Override
            public void onTick(long millisUntilFinished) {
                PauseTimer.this.millisUntilFinished = millisUntilFinished;
                listener.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                millisUntilFinished = 0;
                listener.onFinish();
            }
        };
    }

    public interface PauseTimerListener {
        void onTick(long millisUntilFinished);
        void onFinish();
    }
}