package edu.hw7;

import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class ManyIncrementThreadsTest {
    @RepeatedTest(10)
    public void incrementTest() {
        int value = 0;
        int threads = 1000;

        int result = ManyIncrementThreads.incrementThreads(value, threads);

        assertThat(result).isEqualTo(threads);
    }
}
