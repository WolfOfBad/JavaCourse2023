package edu.hw8.ToxicResponseServer.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private final int port;
    private SocketChannel clientSocket;
    private ByteBuffer buffer;
    private static final int BUFFER_SIZE = 256;

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        try {
            clientSocket = SocketChannel.open(new InetSocketAddress("localhost", port));
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close client", e);
        }
    }

    public String sendMessage(String message) {
        buffer.clear();
        buffer.put(message.getBytes());
        String response;

        try {
            buffer.limit(message.length());
            buffer.position(0);
            clientSocket.write(buffer);

            buffer.clear();
            clientSocket.read(buffer);
            response = new String(buffer.array()).trim();
        } catch (IOException e) {
            throw new RuntimeException("Cannot send message", e);
        }

        return response;
    }
}
