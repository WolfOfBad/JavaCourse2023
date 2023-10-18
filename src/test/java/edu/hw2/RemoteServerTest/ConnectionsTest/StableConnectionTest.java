package edu.hw2.RemoteServerTest.ConnectionsTest;

import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.StableConnection;
import edu.hw2.RemoteServerTest.LoggerStringsGetter;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StableConnectionTest {
    @Test
    @DisplayName("Тест стабильного подключения")
    public void stableConnectionTest() {
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(StableConnection.class);

        try (Connection connection = new StableConnection()) {
            connection.execute("test message");
        } catch (Exception exception) {
            LogManager.getLogger().info(exception.getMessage());
        }

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(0)).isEqualTo("Command executed successfully!");
        assertThat(loggedStrings.get(1)).isEqualTo("Connection closed");
    }
}
