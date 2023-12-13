package edu.hw8;

import edu.hw8.CustomThreadPool.CustomThreadPool;
import edu.hw8.CustomThreadPool.ThreadPool;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomThreadPoolTest {
    private final Map<Long, Long> fibNumbers = Map.of(
        5L, 5L,
        7L, 13L,
        9L, 34L,
        10L, 55L
    );

    @Test
    public void correctWorkTest() {
        ThreadPool pool = CustomThreadPool.create(5);
        pool.start();
        Map<Long, Long> results = Collections.synchronizedMap(new HashMap<>());

        for (var entry : fibNumbers.entrySet()) {
            pool.execute(() -> {
                long result = fib(entry.getKey());
                results.put(entry.getKey(), result);
            });
        }

        try {
            Thread.sleep(5000);
            pool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(results).containsExactlyInAnyOrderEntriesOf(fibNumbers);
    }

    private long fib(long n) {
        long first = 1;
        long second = 1;

        for (long i = 2; i < n; i++) {
            long temp = first + second;
            first = second;
            second = temp;
        }

        return second;
    }
}
