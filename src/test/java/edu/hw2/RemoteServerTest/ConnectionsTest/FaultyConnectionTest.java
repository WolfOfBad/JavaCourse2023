package edu.hw2.RemoteServerTest.ConnectionsTest;

import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.FaultyConnection;
import edu.hw2.RemoteServerTest.LoggerStringsGetter;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FaultyConnectionTest {
    @Test
    @DisplayName("Тест нестабильного подключения")
    public void faultyConnectionTest() {
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(FaultyConnection.class);

        int failedConnectionsCount = 0;
        try (Connection connection = new FaultyConnection()) {
            for (int i = 0; i < 8; i++) {
                try {
                    connection.execute("test message");
                } catch (Exception exception) {
                    failedConnectionsCount++;
                }
            }
        } catch (Exception exception) {
            LogManager.getLogger().info(exception.getMessage());
        }

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.contains("Command executed successfully!")).isTrue();
        assertThat(loggedStrings.contains("Connection closed")).isTrue();
        assertThat(Collections.frequency(loggedStrings, "Command executed successfully!")
            + failedConnectionsCount).isEqualTo(8);
    }
}
