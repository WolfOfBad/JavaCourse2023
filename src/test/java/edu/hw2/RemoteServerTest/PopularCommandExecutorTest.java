package edu.hw2.RemoteServerTest;

import edu.hw2.RemoteServer.ConectionManagers.ConnectionManager;
import edu.hw2.RemoteServer.ConectionManagers.DefaultConnectionManager;
import edu.hw2.RemoteServer.Connections.Connection;
import edu.hw2.RemoteServer.Connections.ConnectionException;
import edu.hw2.RemoteServer.Connections.StableConnection;
import edu.hw2.RemoteServer.PopularCommandExecutor;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Тест успешной отправки")
    public void stableConnectionExecutionTest() throws Exception {
        // Менеджер, который всегда возвращает стабильное соединение
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor();
        ConnectionManager connectionManagerMock = mock(ConnectionManager.class);
        Mockito.when(connectionManagerMock.getConnection()).thenReturn(new StableConnection());

        Field fieldManager = PopularCommandExecutor.class.getDeclaredField("manager");
        fieldManager.setAccessible(true);
        fieldManager.set(popularCommandExecutor, connectionManagerMock);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(StableConnection.class);

        popularCommandExecutor.updatePackages();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.contains("Command executed successfully!")).isTrue();
        assertThat(loggedStrings.contains("Connection closed")).isTrue();
    }

    @Test
    @DisplayName("Тест неуспешной отправки")
    public void defaultManagerExecutionTest() throws Exception {
        // Соединение, которое никогда не отправляет сообщение
        Connection connectionMock = mock(Connection.class);
        Mockito.doThrow(new ConnectionException("Execution failed"))
            .when(connectionMock).execute("apt update && apt upgrade -y");

        // Менеджер который возвращает это соединение
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor();
        ConnectionManager connectionManagerMock = mock(DefaultConnectionManager.class);
        Mockito.when(connectionManagerMock.getConnection()).thenReturn(connectionMock);

        Field fieldManager = PopularCommandExecutor.class.getDeclaredField("manager");
        fieldManager.setAccessible(true);
        fieldManager.set(popularCommandExecutor, connectionManagerMock);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(PopularCommandExecutor.class);

        popularCommandExecutor.updatePackages();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(Collections.frequency(loggedStrings, "Execution failed")).isEqualTo(5);
        assertThat(loggedStrings.contains("Execution failed 5 times")).isTrue();
    }

}
