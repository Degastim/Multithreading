package com.epam.entity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private int capacity;
    int occupiedPlace;
    private static Lock locker = new ReentrantLock();

    private Port() {
    }

    private static class SingletonHolder {
        private static final Port instance = new Port();
    }

    public static Port getInstance() {
        return SingletonHolder.instance;
    }

    public boolean unloading(int number) {
        if (occupiedPlace > number) {
            locker.lock();
            occupiedPlace -= number;
            locker.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean loading(int number) {
        if (capacity < number + occupiedPlace) {
            locker.lock();
            occupiedPlace += number;
            locker.unlock();
            return true;
        } else {
            return false;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void init(int capacity) {
        locker.lock();
        this.capacity = capacity;
        occupiedPlace = 0;
        locker.unlock();
    }
}
