package com.epam.initializer;

import com.epam.entity.Port;
import com.epam.entity.Ship;
import com.epam.state.LoadingShipState;
import com.epam.state.ShipState;
import com.epam.state.UnloadingShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Initializer {
    private static final Logger logger= LogManager.getLogger();
    private static final String PORT_CAPACITY = "capacity";
    private static final String THREAD_NUMBER = "threadNumber";
    public void init(String text) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(text);
        properties.load(inputStream);
        int capacity = Integer.parseInt(properties.getProperty(PORT_CAPACITY));
        int threadNumber = Integer.parseInt(properties.getProperty(THREAD_NUMBER));
        Port port = Port.getInstance();
        port.init(capacity);
        ShipState purpose;
        for (int i = 0; i < threadNumber / 2; i++) {
            purpose = new LoadingShipState();
            Thread thread=new Thread(new Ship(purpose));
            thread.start();
        }
        for (int i = threadNumber / 2; i < threadNumber; i++) {
            purpose = new UnloadingShipState();
            Thread thread=new Thread(new Ship(purpose));
            thread.start();
        }
    }
}

