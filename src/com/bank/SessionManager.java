package com.bank;

import java.util.concurrent.TimeUnit;

public class SessionManager {
    private static final int TIMEOUT_MINUTES = 2;
    private long lastActivityTime;

    public void resetTimer() {
        this.lastActivityTime = System.currentTimeMillis();
    }

    public boolean isTimedOut() {
        long elapsedMillis = System.currentTimeMillis() - lastActivityTime;
        return elapsedMillis > TimeUnit.MINUTES.toMillis(TIMEOUT_MINUTES);
    }
}