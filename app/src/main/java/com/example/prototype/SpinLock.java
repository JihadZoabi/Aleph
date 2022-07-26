package com.example.prototype;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
    public final AtomicBoolean engaged = new AtomicBoolean();
    public void lock() {
        while (!engaged.compareAndSet(false, true));
    }
    public void unlock() {
        engaged.set(false);
    }
}
