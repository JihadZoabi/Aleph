package com.example.prototype;

import java.util.concurrent.Semaphore;

public class LockThatLocks {
    public Semaphore s = new Semaphore(1);
    public void lock() {
        try {
            s.acquire();
        } catch (Exception e) {
            // Yes.
        }
    }
    public void unlock() {
        s.release();
    }
}
