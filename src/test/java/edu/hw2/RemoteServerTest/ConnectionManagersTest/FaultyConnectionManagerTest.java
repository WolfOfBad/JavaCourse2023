package edu.hw2.RemoteServerTest.ConnectionManagersTest;

import edu.hw2.RemoteServer.ConectionManagers.ConnectionManager;
import edu.hw2.RemoteServer.ConectionManagers.FaultyConnectionManager;
import edu.hw2.RemoteServerTest.LoggerStringsGetter;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FaultyConnectionManagerTest {
    @Test
    @DisplayName("Тест нестабильного менеджера подключений")
    public void faultyConnectionManagerTest() {
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(FaultyConnectionManager.class);

        ConnectionManager connectionManager = new FaultyConnectionManager();
        connectionManager.getConnection();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(0)).isEqualTo("Faulty connection is established");
    }
}
