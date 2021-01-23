package com.epam.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger logger = LogManager.getLogger();
    private int capacity;
    private int occupiedPlace;
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
            logger.log(Level.INFO, "Unloading\t" + occupiedPlace);
            return true;
        } else {
            logger.log(Level.INFO, "No product to unloading\t" + occupiedPlace);
            return false;
        }
    }

    public boolean loading(int number) {
        if (capacity < number + occupiedPlace) {
            locker.lock();
            occupiedPlace += number;
            locker.unlock();
            logger.log(Level.INFO, "Loading\t" + occupiedPlace);
            return true;
        } else {
            logger.log(Level.INFO, "No space to loading\t" + occupiedPlace);
            return false;
        }
    }

    public void init(int capacity) {
        locker.lock();
        this.capacity = capacity;
        occupiedPlace = 0;
        locker.unlock();
    }
}
