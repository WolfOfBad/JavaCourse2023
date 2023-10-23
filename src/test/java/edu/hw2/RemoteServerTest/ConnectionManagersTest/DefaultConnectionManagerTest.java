package edu.hw2.RemoteServerTest.ConnectionManagersTest;

import edu.hw2.RemoteServer.ConectionManagers.ConnectionManager;
import edu.hw2.RemoteServer.ConectionManagers.DefaultConnectionManager;
import edu.hw2.RemoteServerTest.LoggerStringsGetter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DefaultConnectionManagerTest {
    @Test
    @DisplayName("Тест стандартного менеджера подключений")
    public void defaultConnectionManagerTest() {
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(DefaultConnectionManager.class);

        ConnectionManager connectionManager = new DefaultConnectionManager();
        for (int i = 0; i < 5; i++) {
            connectionManager.getConnection();
        }

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.contains("Faulty connection is established")).isTrue();
        assertThat(Collections.frequency(loggedStrings, "Stable connection is established")
            + Collections.frequency(loggedStrings, "Faulty connection is established")).isEqualTo(5);
        assertThat(loggedStrings.size()).isEqualTo(5);
    }
}
