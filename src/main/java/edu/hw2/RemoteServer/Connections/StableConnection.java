package edu.hw2.RemoteServer.Connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command) throws ConnectionException {
        // execution...
        logger.info("Command executed successfully!");
    }

    @Override
    public void close() throws InterruptedException {
        // close...
        logger.info("Connection closed");
    }
}
