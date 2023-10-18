package edu.hw2.RemoteServer.ConectionManagers;

import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.FaultyConnection;
import edu.hw2.RemoteServer.Connections.StableConnection;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private final static Random random = new Random();
    private final Logger logger = LogManager.getLogger();
    // Каждые 5 подключений гарантированно выдает нестабильное соединение
    private static final int FAULTY_CONNECTION_RATE = 5;
    private int connectionCount;

    public DefaultConnectionManager() {
        connectionCount = random.nextInt(1, FAULTY_CONNECTION_RATE);
    }

    @Override
    public Connection getConnection() {
        if (connectionCount < FAULTY_CONNECTION_RATE) {
            logger.info("Stable connection is established");
            connectionCount++;
            return new StableConnection();
        } else {
            logger.info("Faulty connection is established");
            connectionCount = random.nextInt(1, FAULTY_CONNECTION_RATE);
            return new FaultyConnection();
        }
    }
}
