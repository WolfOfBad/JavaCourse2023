package edu.hw2.RemoteServer.ConectionManagers;

import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.FaultyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    private final Logger logger = LogManager.getLogger();

    @Override
    public Connection getConnection() {
        logger.info("Faulty connection is established");
        return new FaultyConnection();
    }
}
