package com.epam.entity;

import com.epam.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Ship implements Runnable {
    Logger logger= LogManager.getLogger();
    ShipState state;
    int cargo;
    public Ship(ShipState purpose) {
        this.state = purpose;
    }

    public void run() {
        try {
            state.doWork(cargo);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR,e);
        }
    }
}
