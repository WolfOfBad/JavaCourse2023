package edu.hw7;

import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class ManyIncrementThreadsTest {
    @RepeatedTest(10)
    public void incrementTest() {
        int value = 0;
        int add = 1000;
        int threads = Runtime.getRuntime().availableProcessors();

        int result = ManyIncrementThreads.incrementThreads(value, add, threads);

        assertThat(result).isEqualTo(value + add);
    }
}
