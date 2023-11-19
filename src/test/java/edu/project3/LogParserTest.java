package edu.project3;

import edu.project3.Parsers.ArgumentsParser.UserArguments;
import edu.project3.Parsers.LogParser.LogParser;
import edu.project3.Parsers.LogParser.LogRecord;
import edu.project3.Statistics.Records.HTTPCode;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogParserTest {
    @Test
    public void parserTest() throws IOException {
        Path logFile = Path.of("src/test/resources/project3/logs.txt");
        UserArguments arguments = new UserArguments(
            List.of(logFile),
            OffsetDateTime.of(2015, 5, 18, 0, 0, 0, 0, ZoneOffset.UTC),
            OffsetDateTime.of(2015, 6, 2, 0, 0, 0, 0, ZoneOffset.UTC),
            null,
            null
        );

        LogParser parser = new LogParser();
        List<LogRecord> result = parser.parse(arguments);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).ip()).isEqualTo("54.183.198.11");
        assertThat(result.get(0).user()).isEqualTo("-");
        assertThat(result.get(0).time()).isEqualTo(OffsetDateTime.of(
            2015, 5, 28, 2, 5, 56, 0, ZoneOffset.UTC
        ));
        assertThat(result.get(0).request()).isEqualTo("GET /downloads/product_1 HTTP/1.1");
        assertThat(result.get(0).resource()).isEqualTo("/downloads/product_1");
        assertThat(result.get(0).code()).isEqualTo(new HTTPCode(404));
        assertThat(result.get(0).bytesSent()).isEqualTo(336);
        assertThat(result.get(0).httpReferer()).isEqualTo("-");
        assertThat(result.get(0).httpUserAgent()).isEqualTo("Debian APT-HTTP/1.3 (1.0.1ubuntu2)");
        assertThat(result.get(0).file()).isEqualTo(logFile);
    }

}
