package edu.hw6;

import edu.hw6.PortsChecker.PortInfo;
import edu.hw6.PortsChecker.PortsChecker;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PortsCheckerTest {
    private final Map<Integer, String> ports = PortInfo.POPULAR_PORTS;

    private int findOpenServerSocket() {
        for (int port : ports.keySet()) {
            try (ServerSocket socket = new ServerSocket(port)) {
                return port;
            } catch (IOException ignored) {
            }
        }
        return -1;
    }

    private int findOpenDatagramSocket() {
        for (int port : ports.keySet()) {
            try (DatagramSocket socket = new DatagramSocket(port)) {
                return port;
            } catch (IOException ignored) {
            }
        }
        return -1;
    }

    @Test
    public void popularPortsTest() throws IOException {
        // найдем пустые порты и проверим, что они возвращаются как открытые
        int freeServerSocketPort = findOpenServerSocket();
        int freeDatagramSocket = findOpenDatagramSocket();

        PortsChecker checker = new PortsChecker();
        List<PortInfo> portsInfo = checker.getPopularPortsInfo();

        assertThat(portsInfo.contains(new PortInfo(
            freeServerSocketPort,
            "TCP",
            false,
            ports.get(freeServerSocketPort)
        ))).isTrue();
        assertThat(portsInfo.contains(new PortInfo(
            freeDatagramSocket,
            "UDP",
            false,
            ports.get(freeDatagramSocket)
        ))).isTrue();

        // закроем порты и проверим, что вернется информация, что они закрыты
        try (ServerSocket serverSocket = new ServerSocket(freeServerSocketPort);
             DatagramSocket datagramSocket = new DatagramSocket(freeDatagramSocket)) {
            portsInfo = checker.getPopularPortsInfo();

            assertThat(portsInfo.contains(new PortInfo(
                freeServerSocketPort,
                "TCP",
                true,
                ports.get(freeServerSocketPort)
            ))).isTrue();
            assertThat(portsInfo.contains(new PortInfo(
                freeDatagramSocket,
                "UDP",
                true,
                ports.get(freeDatagramSocket)
            ))).isTrue();
        }
    }
}
