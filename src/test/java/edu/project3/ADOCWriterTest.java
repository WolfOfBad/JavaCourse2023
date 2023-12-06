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
        ==== Общая информация
        [cols=2]
        |====
        |Метрика
        |Значение
        |Файлы
        |logs.txt
        |Начало логгирования
        |2023-11-19T20:00Z
        |Конец логгирования
        |2023-11-19T22:00Z
        |Количество запросов
        |3
        |Средний размер ответа
        |300
        |====
        ==== Запрашиваемые ресурсы
        [cols=2]
        |====
        |Ресурс
        |Количество
        |test1
        |2
        |test2
        |1
        |====
        ==== Коды ответа
        [cols=3]
        |====
        |Код
        |Имя
        |Количество
        |404
        |Not Found
        |2
        |200
        |OK
        |1
        |====
        ==== Запросы по дням
        [cols=2]
        |====
        |День
        |Количество запросов
        |2023-11-18
        |2
        |2023-11-19
        |1
        |====
        ==== Запросы по часам
        [cols=2]
        |====
        |Час
        |Количество запросов
        |15
        |1
        |22
        |2
        |====
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

        assertThat(Files.readString(Path.of(dir.toString(), "logStats.adoc"))
            .replaceAll("\r\n", "\n")).isEqualTo(expected);
    }
}
