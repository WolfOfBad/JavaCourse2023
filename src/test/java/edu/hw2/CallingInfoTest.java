package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static edu.hw2.CallingInfoGetter.callingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {

    @Test
    public void traceInfoTest() {
        var info = callingInfo();
        Logger logger = LogManager.getLogger();

        logger.info(info.className());
        logger.info(info.methodName());

        assertThat(info.className()).isEqualTo("edu.hw2.CallingInfoTest");
        assertThat(info.methodName()).isEqualTo("traceInfoTest");
    }

}
