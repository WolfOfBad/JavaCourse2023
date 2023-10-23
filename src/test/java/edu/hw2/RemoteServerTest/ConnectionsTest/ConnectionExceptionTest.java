package edu.hw2.RemoteServerTest.ConnectionsTest;

import edu.hw2.RemoteServer.Connections.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConnectionExceptionTest {
    @Test
    @DisplayName("Тест исключения подключения")
    public void connectionExceptionTest() {
        Exception exception = new Exception("Some exception");

        Exception connectionException = new ConnectionException("Connection failed");
        Exception connectionExceptionWithCause = new ConnectionException("Connection failed", exception);

        assertThat(connectionException).hasMessage("Connection failed");
        assertThat(connectionExceptionWithCause).hasMessage("Connection failed");
        assertThat(connectionExceptionWithCause).hasCause(exception);
    }
}
