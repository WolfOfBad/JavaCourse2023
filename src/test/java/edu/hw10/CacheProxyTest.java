package edu.hw10;

import edu.hw10.cacheProxy.CacheProxy;
import edu.hw10.cacheProxy.FibCalculator;
import edu.hw10.cacheProxy.SimpleFibCalculator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    @Test
    public void test() throws IOException {
        FibCalculator obj = new SimpleFibCalculator();

        Path cacheDir = Path.of("./cache.tmp");
        FibCalculator proxy = (FibCalculator) CacheProxy.create(
            obj,
            FibCalculator.class,
            cacheDir
        );

        long result1 = proxy.calculate(3);
        long result2 = proxy.calculate(4);
        long result3 = proxy.calculate(3);

        List<String> lines = Files.readAllLines(cacheDir);
        Files.delete(cacheDir);

        assertThat(result1).isEqualTo(2);
        assertThat(result2).isEqualTo(3);
        assertThat(result3).isEqualTo(2);

        assertThat(lines).size().isEqualTo(2);
        assertThat(lines.get(0)).isEqualTo("Method=calculate(long) args=[3] returned=2");
        assertThat(lines.get(1)).isEqualTo("Method=calculate(long) args=[4] returned=3");
    }

}
