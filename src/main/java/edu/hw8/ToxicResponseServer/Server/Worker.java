package edu.hw8.ToxicResponseServer.Server;

import edu.hw8.ToxicResponseServer.Server.PhraseFinder.PhraseFinder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Worker implements Runnable {
    private final SocketChannel client;
    private final Selector selector;
    private final PhraseFinder finder;
    private static final int BUFFER_SIZE = 256;

    public Worker(SocketChannel client, Selector selector, PhraseFinder finder) {
        this.client = client;
        this.selector = selector;
        this.finder = finder;
    }

    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int r = client.read(buffer);
            if (r == -1) {
                client.close();
            } else {
                String response = finder.find(new String(buffer.array()).trim());
                buffer.clear();
                buffer.put(response.getBytes());
                buffer.flip();
                client.write(buffer);
                buffer.clear();
                client.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot send message", e);
        }
    }
}
