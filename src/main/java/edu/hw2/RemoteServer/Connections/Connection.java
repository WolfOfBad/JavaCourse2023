package edu.hw2.RemoteServer.Connections;

public interface Connection extends AutoCloseable {
    void execute(String command) throws ConnectionException;
}
