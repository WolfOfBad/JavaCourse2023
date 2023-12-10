package edu.hw9;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @Test
    public void concurrentTest() throws InterruptedException {
        StatsCollector collector = new StatsCollector();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                collector.push("metric", new double[] { 1 });
            }));
            threads.getLast().start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        StatsCollector.Metric metric = collector.stats().get("metric");
        assertThat(metric.average()).isEqualTo(1.0);
        assertThat(metric.sum()).isEqualTo(100);
        assertThat(metric.min()).isEqualTo(1);
        assertThat(metric.max()).isEqualTo(1);
        assertThat(metric.size()).isEqualTo(100);
    }

}
