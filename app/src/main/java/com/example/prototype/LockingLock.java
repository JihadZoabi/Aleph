package com.example.prototype;

public class LockingLock {
    private boolean free = true;
    public synchronized void lock() {
        free = false;
        notifyAll();
    }
    public synchronized void unlock() {
        while (!free)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
