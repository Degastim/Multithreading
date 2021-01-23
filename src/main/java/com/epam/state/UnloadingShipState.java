package com.epam.state;


import com.epam.entity.Port;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnloadingShipState implements ShipState {
    Port port = Port.getInstance();
    Lock locker = new ReentrantLock();
    @Override
    public void doWork(int number) throws InterruptedException {
        if (locker.tryLock(10, TimeUnit.SECONDS)) {
            port.loading(number);
        }
        locker.unlock();
    }
}
