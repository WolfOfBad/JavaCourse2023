package edu.hw8.ToxicResponseTest;

import edu.hw8.ToxicResponseServer.Client.Client;
import edu.hw8.ToxicResponseServer.Server.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ToxicResponseServerTest {
    @Test
    public void serverTest() throws InterruptedException {
        Server server = new Server(5454, 4);
        new Thread(server).start();
        Thread.sleep(1000);
        AtomicInteger responses = new AtomicInteger();

        List<Thread> pool = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pool.add(new Thread(() -> {
                Client client = new Client(5454);
                client.start();
                client.sendMessage("msg1");
                responses.getAndIncrement();
                client.sendMessage("msg2");
                responses.getAndIncrement();
                client.sendMessage("msg3");
                responses.getAndIncrement();
                client.close();
            }));
            pool.getLast().start();
        }

        for (Thread thread : pool) {
            thread.join();
        }

        server.close();

        assertThat(responses.get()).isEqualTo(30);
    }
}
