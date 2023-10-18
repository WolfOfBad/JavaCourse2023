package edu.hw2.RemoteServer.Connections;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Exception cause) {
        super(message, cause);
    }
}
