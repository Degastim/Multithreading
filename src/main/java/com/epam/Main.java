package com.epam;

import com.epam.initializer.Initializer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Initializer.init("src/main/resources/data/configuration.properties");
    }
}
