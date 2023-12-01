package edu.hw8;

import edu.hw8.Bruteforce.Bruteforce;
import edu.hw8.Bruteforce.ParallelBruteforce;
import edu.hw8.Bruteforce.SimpleBruteforce;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BruteforceTest {
    @Test
    @DisplayName("Тест однопоточного подбора")
    public void simpleBruteforceTest() {
        String password = "abca";
        String hash = DigestUtils.md5Hex(password);

        Bruteforce obj = new SimpleBruteforce(5);
        var result = obj.find(Map.of("1", hash));

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of("1", password));
    }

    @Test
    @DisplayName("Тест параллельного подбора")
    public void parallelBruteforceTest() {
        String password = "abca";
        String hash = DigestUtils.md5Hex(password);

        Bruteforce obj = new ParallelBruteforce(5, Runtime.getRuntime().availableProcessors());
        var result = obj.find(Map.of("1", hash));

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of("1", password));
    }
}
