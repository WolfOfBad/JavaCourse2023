package edu.hw6.PortsChecker;

import java.util.Map;

public record PortInfo(Integer port,
                       String protocol,
                       boolean isConnected,
                       String service) {
    @SuppressWarnings("MagicNumber")
    public static final Map<Integer, String> POPULAR_PORTS = Map.ofEntries(
        Map.entry(21, "FTP"),
        Map.entry(22, "SSH"),
        Map.entry(23, "Telnet"),
        Map.entry(25, "SMTP"),
        Map.entry(53, "DNS"),
        Map.entry(80, "HTTP"),
        Map.entry(110, "POP3"),
        Map.entry(123, "NTP"),
        Map.entry(143, "IMAP"),
        Map.entry(443, "HTTPS"),
        Map.entry(445, "SMB"),
        Map.entry(500, "IKE"),
        Map.entry(873, "Rsync"),
        Map.entry(1080, "SOCKS"),
        Map.entry(1443, "MSSQL"),
        Map.entry(1521, "Oracle Database"),
        Map.entry(1701, "L2TP"),
        Map.entry(1723, "PPTP"),
        Map.entry(2049, "NFS"),
        Map.entry(3306, "MySQL Database"),
        Map.entry(3128, "HTTPS Proxy"),
        Map.entry(3389, "Remote Desktop Protocol (RDP)"),
        Map.entry(5090, "VNC"),
        Map.entry(5353, "mDNS (Multicast Domain Name System"),
        Map.entry(5432, "PostgreSQL Database"),
        Map.entry(5672, "AMQP (Advanced Message Queuing Protocol"),
        Map.entry(5722, "SMB2"),
        Map.entry(6379, "Redis"),
        Map.entry(8080, "HTTP Proxy"),
        Map.entry(11211, "Memcached"),
        Map.entry(27017, "MongoDB Database"),
        Map.entry(49152, "Windows RPC (Remote Procedure Call)")
    );
}
