package edu.hw2.RemoteServer.Connections;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final Random random = new Random();
    private final Logger logger = LogManager.getLogger();
    // Каждые 7 попыток исполнения команда гарантированно исполнится
    private static final int SUCCESS_EXECUTION_RATE = 7;
    private int failExecutionCount;

    public FaultyConnection() {
        failExecutionCount = random.nextInt(1, SUCCESS_EXECUTION_RATE);
    }

    @Override
    public void execute(String command) throws ConnectionException {
        if (failExecutionCount < SUCCESS_EXECUTION_RATE) {
            // execution...
            // execution failed
            failExecutionCount++;
            throw new ConnectionException("Execution failed");
        } else {
            failExecutionCount = random.nextInt(1, SUCCESS_EXECUTION_RATE);
            logger.info("Command executed successfully!");
            // execution...
        }
    }

    @Override
    public void close() throws InterruptedException {
        // close...
        logger.info("Connection closed");
    }
}
