package com.epam.state;


import com.epam.entity.Port;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnloadingShipState implements ShipState {
    Port port = Port.getInstance();
    Lock locker = new ReentrantLock();
    Condition condition = locker.newCondition();
    @Override
    public void doWork(int number) throws InterruptedException {
        while (port.unloading(number)) {
            condition.await();
        }
        condition.signalAll();
    }
}
