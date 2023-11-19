package edu.project3;

import edu.project3.Parsers.LogParser.LogRecord;
import edu.project3.Statistics.LogStatistics;
import edu.project3.Statistics.Records.GeneralInfo;
import edu.project3.Statistics.Records.HTTPCode;
import edu.project3.Statistics.Records.LogReport;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogStatisticsTest {
    @Test
    public void statisticsTest() {
        List<LogRecord> logs = List.of(
            new LogRecord(
                "-",
                "-",
                OffsetDateTime.of(2023, 11, 19, 7, 30, 0, 0, ZoneOffset.UTC),
                "-",
                "test1.txt",
                new HTTPCode(304),
                400,
                "-",
                "-",
                Path.of("logs1.txt")
            ),
            new LogRecord(
                "-",
                "-",
                OffsetDateTime.of(2023, 11, 18, 12, 0, 0, 0, ZoneOffset.UTC),
                "-",
                "test2.txt",
                new HTTPCode(404),
                300,
                "-",
                "-",
                Path.of("logs2.txt")
            ),
            new LogRecord(
                "-",
                "-",
                OffsetDateTime.of(2023, 11, 19, 17, 40, 0, 0, ZoneOffset.UTC),
                "-",
                "test1.txt",
                new HTTPCode(200),
                200,
                "-",
                "-",
                Path.of("logs1.txt")
            )
        );

        LogStatistics statistics = new LogStatistics();
        LogReport result = statistics.getLogReport(logs);

        GeneralInfo info = result.general();
        Map<String, Integer> resources = result.requestedResources();
        Map<HTTPCode, Integer> codes = result.codes();
        Map<LocalDate, Integer> days = result.dayRequests();
        Map<Integer, Integer> times = result.timeRequests();

        assertThat(info.files()).isEqualTo("(2)");
        assertThat(info.from()).isEqualTo(OffsetDateTime.of(
            2023, 11, 18, 12, 0, 0, 0, ZoneOffset.UTC));
        assertThat(info.to()).isEqualTo(OffsetDateTime.of(
            2023, 11, 19, 17, 40, 0, 0, ZoneOffset.UTC));
        assertThat(info.requests()).isEqualTo(3);
        assertThat(info.averageResponse()).isEqualTo(300);

        assertThat(resources.size()).isEqualTo(2);
        assertThat(resources.get("test1.txt")).isEqualTo(2);
        assertThat(resources.get("test2.txt")).isEqualTo(1);

        assertThat(codes.size()).isEqualTo(3);
        assertThat(codes.get(new HTTPCode(200))).isEqualTo(1);
        assertThat(codes.get(new HTTPCode(304))).isEqualTo(1);
        assertThat(codes.get(new HTTPCode(404))).isEqualTo(1);

        assertThat(days.size()).isEqualTo(2);
        assertThat(days.get(LocalDate.of(2023, 11, 19))).isEqualTo(2);
        assertThat(days.get(LocalDate.of(2023, 11, 18))).isEqualTo(1);

        assertThat(times.get(7)).isEqualTo(1);
        assertThat(times.get(12)).isEqualTo(1);
        assertThat(times.get(17)).isEqualTo(1);
    }
}
