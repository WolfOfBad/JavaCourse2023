package edu.hw9;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    private final Map<String, Metric> metrics = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void push(String metricName, double[] values) {
        lock.writeLock().lock();
        try {
            metrics.put(metricName, Metric.update(metrics.get(metricName), values));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, Metric> stats() {
        lock.readLock().lock();
        try {
            return new HashMap<>(metrics);
        } finally {
            lock.readLock().unlock();
        }
    }

    public record Metric(
        double sum,
        double average,
        double max,
        double min,
        int size
    ) {
        private static Metric update(Metric metric, double[] values) {
            double sum = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            int size = values.length;

            if (metric != null) {
                sum = metric.sum;
                max = metric.max;
                min = metric.min;
                size = metric.size + values.length;
            }

            for (double value : values) {
                sum += value;
                max = Math.max(max, value);
                min = Math.min(min, value);
            }

            return new Metric(
                sum,
                sum / size,
                max, min,
                size
            );
        }
    }
}
