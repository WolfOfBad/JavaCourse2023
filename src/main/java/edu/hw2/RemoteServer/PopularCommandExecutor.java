package edu.hw2.RemoteServer;

import edu.hw2.RemoteServer.ConectionManagers.ConnectionManager;
import edu.hw2.RemoteServer.ConectionManagers.DefaultConnectionManager;
import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager = new DefaultConnectionManager();
    private final int maxAttempts = 5;
    private final Logger logger = LogManager.getLogger();

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        try (Connection connection = manager.getConnection()) {
            int attempts = 0;
            while (attempts < maxAttempts) {
                try {
                    connection.execute(command);
                    break;
                } catch (ConnectionException exception) {
                    logger.info(exception.getMessage());
                    attempts++;
                    if (attempts == maxAttempts) {
                        throw new ConnectionException("Execution failed " + maxAttempts + " times", exception);
                    }
                }
            }
        } catch (Exception exception) {
            logger.info(exception.getMessage(), (Object) exception.getStackTrace());
        }

    }
}
