package edu.hw6.PortsChecker;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PortsChecker {
    @SuppressWarnings("MultipleStringLiterals")
    public List<PortInfo> getPopularPortsInfo() {
        List<PortInfo> result = new ArrayList<>(PortInfo.POPULAR_PORTS.size());

        for (var port : PortInfo.POPULAR_PORTS
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .toList()) {

            try (ServerSocket serverSocket = new ServerSocket(port.getKey())) {
                result.add(new PortInfo(port.getKey(), "TCP", false, port.getValue()));
            } catch (IOException e) {
                result.add(new PortInfo(port.getKey(), "TCP", true, port.getValue()));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port.getKey())) {
                result.add(new PortInfo(port.getKey(), "UDP", false, port.getValue()));
            } catch (SocketException e) {
                result.add(new PortInfo(port.getKey(), "UDP", true, port.getValue()));
            }
        }
        return result;
    }
}
