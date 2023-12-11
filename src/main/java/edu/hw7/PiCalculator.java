package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiCalculator {
    /**
     * Однопоточное вычисление числа пи
     */
    @SuppressWarnings("MagicNumber")
    public double getPi(int dots) {
        return 4 * ((double) countCircleDots(dots) / (double) dots);
    }

    /**
     * Многопоточное вычисление числа пи
     */
    @SuppressWarnings("MagicNumber")
    public double getPi(int dots, int threads) {
        AtomicInteger circleCount = new AtomicInteger();
        int iterations = dots / threads + threads;

        List<Thread> pool = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            pool.add(new Thread(() -> {
                int hits = countCircleDots(iterations);
                circleCount.getAndAdd(hits);
            }));
            pool.getLast().start();
        }

        for (Thread thread : pool) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return 4 * ((double) circleCount.get() / (double) (iterations * threads));
    }

    @SuppressWarnings("MagicNumber")
    private int countCircleDots(int dots) {
        Random random = ThreadLocalRandom.current();
        int circleCount = 0;

        for (int i = 0; i < dots; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= 0.25) {
                circleCount++;
            }
        }

        return circleCount;
    }
}
