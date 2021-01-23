package com.epam.entity;

import com.epam.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Ship implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private static ShipState state;
    int cargo;

    public Ship(ShipState state) {
        this.state = state;
    }

    public void run() {
        logger.log(Level.INFO, state);
        try {
            state.doWork(cargo);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        logger.log(Level.INFO, "end of thread work ");
    }
}
