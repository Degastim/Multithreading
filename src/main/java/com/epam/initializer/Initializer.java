package com.epam.initializer;

import com.epam.entity.Port;
import com.epam.entity.Ship;
import com.epam.state.LoadingShipState;
import com.epam.state.ShipState;
import com.epam.state.UnloadingShipState;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Initializer {
    private static final String PORT_CAPACITY="capacity";
    private static final String THREAD_NUMBER="threadNumber";
    public static void init(String text) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream(text));
        int capacity=Integer.parseInt(properties.getProperty(PORT_CAPACITY));
        int threadNumber= Integer.parseInt(properties.getProperty(THREAD_NUMBER));
        Port port=Port.getInstance();
        port.init(capacity);
        ShipState purpose;
        for (int i=0;i<threadNumber;i++) {
            int number=new Random().nextInt(2);
            switch (number){
                case(0):
                    purpose= new LoadingShipState();
                    break;
                case(1):
                    purpose= new UnloadingShipState();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + number);
            }
            Thread thread=new Thread(new Ship(purpose));
            thread.start();
        }
    }
}
