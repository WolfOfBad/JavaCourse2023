package edu.hw6.PortsChecker;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record PortInfo(Integer port,
                       String protocol,
                       boolean isConnected,
                       String service) {
    @SuppressWarnings("MagicNumber")
    public static final Map<Integer, String> POPULAR_PORTS = Stream.of(new Object[][] {
        {21, "FTP"},
        {22, "SSH"},
        {23, "Telnet"},
        {25, "SMTP"},
        {53, "DNS"},
        {80, "HTTP"},
        {110, "POP3"},
        {123, "NTP"},
        {143, "IMAP"},
        {443, "HTTPS"},
        {445, "SMB"},
        {500, "IKE"},
        {873, "Rsync"},
        {1080, "SOCKS"},
        {1443, "MSSQL"},
        {1521, "Oracle Database"},
        {1701, "L2TP"},
        {1723, "PPTP"},
        {2049, "NFS"},
        {3306, "MySQL Database"},
        {3128, "HTTPS Proxy"},
        {3389, "Remote Desktop Protocol (RDP)"},
        {5090, "VNC"},
        {5353, "mDNS (Multicast Domain Name System"},
        {5432, "PostgreSQL Database"},
        {5672, "AMQP (Advanced Message Queuing Protocol"},
        {5722, "SMB2"},
        {6379, "Redis"},
        {8080, "HTTP Proxy"},
        {11211, "Memcached"},
        {27017, "MongoDB Database"},
        {49152, "Windows RPC (Remote Procedure Call)"},
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
}
