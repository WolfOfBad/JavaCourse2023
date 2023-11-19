package edu.project3;

import edu.project3.Output.ADOCWriter;
import edu.project3.Output.StatisticsWriter;
import edu.project3.Statistics.Records.GeneralInfo;
import edu.project3.Statistics.Records.HTTPCode;
import edu.project3.Statistics.Records.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ADOCWriterTest {
    private final String expected = """
        ==== Общая информация\r
        [cols=2]\r
        |====\r
        |Метрика\r
        |Значение\r
        |Файлы\r
        |logs.txt\r
        |Начало логгирования\r
        |2023-11-19T20:00Z\r
        |Конец логгирования\r
        |2023-11-19T22:00Z\r
        |Количество запросов\r
        |3\r
        |Средний размер ответа\r
        |300\r
        |====\r
        ==== Запрашиваемые ресурсы\r
        [cols=2]\r
        |====\r
        |Ресурс\r
        |Количество\r
        |test1\r
        |2\r
        |test2\r
        |1\r
        |====\r
        ==== Коды ответа\r
        [cols=3]\r
        |====\r
        |Код\r
        |Имя\r
        |Количество\r
        |404\r
        |Not Found\r
        |2\r
        |200\r
        |OK\r
        |1\r
        |====\r
        ==== Запросы по дням\r
        [cols=2]\r
        |====\r
        |День\r
        |Количество запросов\r
        |2023-11-18\r
        |2\r
        |2023-11-19\r
        |1\r
        |====\r
        ==== Запросы по часам\r
        [cols=2]\r
        |====\r
        |Час\r
        |Количество запросов\r
        |15\r
        |1\r
        |22\r
        |2\r
        |====\r
        """;

    @Test
    public void writerTest(@TempDir Path dir) throws IOException {
        LogReport report = new LogReport(
            new GeneralInfo(
                "logs.txt",
                OffsetDateTime.of(
                    2023, 11, 19, 20, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(
                    2023, 11, 19, 22, 0, 0, 0, ZoneOffset.UTC),
                3,
                300
            ),
            Map.of("test1", 2, "test2", 1),
            Map.of(new HTTPCode(200), 1, new HTTPCode(404), 2),
            Map.of(LocalDate.of(2023, 11, 19), 1,
                LocalDate.of(2023, 11, 18), 2
            ),
            Map.of(15, 1, 22, 2)
        );

        StatisticsWriter writer = new ADOCWriter();
        writer.writeStatistics(report, dir);

        assertThat(Files.readString(Path.of(dir.toString(), "logStats.adoc"))).isEqualTo(expected);
    }
}
