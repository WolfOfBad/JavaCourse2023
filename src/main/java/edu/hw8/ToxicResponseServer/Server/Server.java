package edu.hw8.ToxicResponseServer.Server;

import edu.hw8.ToxicResponseServer.Server.PhraseFinder.SimplePhraseFinder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server implements Runnable {
    private final int port;
    private Selector selector;
    private ServerSocketChannel serverSocket;
    private final ExecutorService threadPool;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public Server(int port, int threads) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void run() {
        startServer();

        while (isRunning.get()) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    }

                    if (key.isReadable()) {
                        answer(key);
                    }

                    iterator.remove();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error while executing server", e);
            }
        }

        try {
            selector.close();
            threadPool.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close server", e);
        }
    }

    public void startServer() {
        try {
            serverSocket = ServerSocketChannel.open();
            selector = Selector.open();

            serverSocket.bind(new InetSocketAddress("localhost", port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            isRunning.set(true);
        } catch (IOException e) {
            throw new RuntimeException("Cannot start server", e);
        }
    }

    private void answer(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        client.register(selector, SelectionKey.OP_WRITE);
        threadPool.submit(new Worker(client, selector, new SimplePhraseFinder()));
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    public void close() {
        isRunning.set(false);
    }

}
