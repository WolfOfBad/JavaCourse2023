package edu.project3;

import edu.project3.Parsers.LogParser.LogRecord;
import edu.project3.Statistics.LogStatistics;
import edu.project3.Statistics.Records.HTTPCode;
import edu.project3.Statistics.Records.LogReport;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogStatisticsTest {
    private static final OffsetDateTime from = OffsetDateTime.of(
        2023, 11, 20, 10, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetDateTime to = OffsetDateTime.of(
        2023, 11, 21, 12, 30, 0, 0, ZoneOffset.UTC);
    private static final LogRecord log1 = new LogRecord(
        "-",
        "-",
        to,
        "-",
        "resource1",
        new HTTPCode(404),
        400,
        "-",
        "-",
        Path.of("test.txt")
    );
    private static final LogRecord log2 = new LogRecord(
        "-",
        "-",
        from,
        "-",
        "resource2",
        new HTTPCode(200),
        200,
        "-",
        "-",
        Path.of("test1.txt")
    );

    @Test
    @DisplayName("Тест открытия одного файла")
    public void oneFileOpenTest() {
        List<LogRecord> logs = List.of(log1);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.general().files()).isEqualTo("test.txt");
    }

    @Test
    @DisplayName("Тест открытия нескольких файлов")
    public void manyFilesOpenTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.general().files()).isEqualTo("(2)");
    }

    @Test
    @DisplayName("Тест получения времени логов")
    public void timeTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.general().from()).isEqualTo(from);
        assertThat(report.general().to()).isEqualTo(to);
    }

    private static Arguments[] timeArgs() {
        return new Arguments[] {
            Arguments.of(List.of(log1)),
            Arguments.of(List.of(log1, log2))
        };
    }

    @ParameterizedTest
    @MethodSource("timeArgs")
    @DisplayName("Тест получения количество запросов")
    public void requestsTest(List<LogRecord> logs) {
        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.general().requests()).isEqualTo(logs.size());
    }

    @Test
    @DisplayName("Тест среднего ответа")
    public void averageResponseTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.general().averageResponse()).isEqualTo(300);
    }

    @Test
    @DisplayName("Тест получения запрошенных ресурсов")
    public void requestedResourcesTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.requestedResources()).isEqualTo(Map.of("resource1", 1, "resource2", 1));
    }

    @Test
    @DisplayName("Тест получения кодов ответа")
    public void codesTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.codes()).isEqualTo(Map.of(
            new HTTPCode(404), 1,
            new HTTPCode(200), 1
        ));
    }

    @Test
    @DisplayName("Тест получения количества запросов по дням")
    public void dayRequestsTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        assertThat(report.dayRequests()).isEqualTo(Map.of(
            from.toLocalDate(), 1,
            to.toLocalDate(), 1
        ));
    }

    @Test
    @DisplayName("Тест получения запросов по часам")
    public void timeRequestsTest() {
        List<LogRecord> logs = List.of(log1, log2);

        LogStatistics statistics = new LogStatistics();
        LogReport report = statistics.getLogReport(logs);

        for (var entry : report.timeRequests().entrySet()) {
            if (entry.getKey() == log1.time().getHour()
                || entry.getKey() == log2.time().getHour()) {
                assertThat(entry.getValue()).isEqualTo(1);
            } else {
                assertThat(entry.getValue()).isEqualTo(0);
            }
        }
    }

}
