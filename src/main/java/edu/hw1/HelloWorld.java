package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HelloWorld {
    private HelloWorld() {
    }

    public static void helloWorld() {
        Logger logger = LogManager.getLogger();
        logger.info("Hello world!");
    }
}
