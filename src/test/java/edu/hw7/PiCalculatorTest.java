package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PiCalculatorTest {
    private static Arguments[] args() {
        return new Arguments[] {
            Arguments.of(10_000_00),
            Arguments.of(100_000_00),
            Arguments.of(1_000_000_00),
        };
    }

    private final Logger logger = LogManager.getLogger();

    @ParameterizedTest
    @MethodSource("args")
    @DisplayName("Тест однопоточного вычисления")
    public void singleThreadCountTest(int dots) {
        logger.info("Simulations: " + dots);
        double libPi = Math.PI;

        PiCalculator obj = new PiCalculator();

        long time = System.nanoTime();
        double PI = obj.getPi(dots);
        time = System.nanoTime() - time;

        logger.info("Library PI = " + libPi);
        logger.info("Single thread PI = " + PI);
        logger.info("Error = " + Math.abs(libPi - PI));
        logger.info("Time: " + time + "ns");

        assertThat(PI).isGreaterThan(3.1);
        assertThat(PI).isLessThan(3.2);
    }

    @ParameterizedTest
    @MethodSource("args")
    @DisplayName("Тест многопоточного вычисления")
    public void parallelCountTest(int dots) {
        logger.info("Simulations: " + dots);
        double libPi = Math.PI;

        PiCalculator obj = new PiCalculator();

        long time = System.nanoTime();
        double PI = obj.getPi(dots, Runtime.getRuntime().availableProcessors());
        time = System.nanoTime() - time;

        logger.info("Library PI = " + libPi);
        logger.info("Many threads PI = " + PI);
        logger.info("Error = " + Math.abs(libPi - PI));
        logger.info("Time: " + time + "ns");

        assertThat(PI).isGreaterThan(3.1);
        assertThat(PI).isLessThan(3.2);
    }

    /*
    В режиме покрытия кода многопоточный режим медленее однопоточного,
    поэтому билд на гитхабе фейлится, хотя при обычном запуске все проходит
     */
    @Test
    @Disabled
    @DisplayName("Проверка, что многопоточное вычисление быстрее однопоточного")
    public void speedTest() {
        int dots = 100_000_000;

        PiCalculator obj = new PiCalculator();

        long singleTime = System.nanoTime();
        obj.getPi(dots);
        singleTime = System.nanoTime() - singleTime;

        long parallelTime = System.nanoTime();
        obj.getPi(dots, Runtime.getRuntime().availableProcessors());
        parallelTime = System.nanoTime() - parallelTime;

        logger.info("Single time: " + singleTime);
        logger.info("Parallel time: " + parallelTime);

        assertThat(parallelTime).isLessThan(singleTime);
    }

}
