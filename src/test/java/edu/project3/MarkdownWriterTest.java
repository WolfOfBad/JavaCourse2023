package edu.project3;

import edu.project3.Output.MarkdownWriter;
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

public class MarkdownWriterTest {
    private final String expected = """
        #### Общая информация\r
        |Метрика|Значение|\r
        |:------|-------:|\r
        |Файлы|logs.txt|\r
        |Начало логгирования|2023-11-19T20:00Z|\r
        |Конец логгирования|2023-11-19T22:00Z|\r
        |Количество запросов|3|\r
        |Средний размер ответа|300|\r
        #### Запрашиваемые ресурсы\r
        |Ресурс|Количество|\r
        |:-----|-------:|\r
        |test1|2|\r
        |test2|1|\r
        #### Коды ответа\r
        |Код|Имя|Количество|\r
        |:--|:-:|---------:|\r
        |404|Not Found|2|\r
        |200|OK|1|\r
        #### Запросы по дням\r
        |День|Количество запросов|\r
        |:---|------------------:|\r
        |2023-11-18|2|\r
        |2023-11-19|1|\r
        #### Запросы по часам\r
        |Час|Количество запросов|\r
        |:--|------------------:|\r
        |15|1|\r
        |22|2|\r
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
            Map.of(
                LocalDate.of(2023, 11, 19), 1,
                LocalDate.of(2023, 11, 18), 2
            ),
            Map.of(15, 1, 22, 2)
        );

        StatisticsWriter writer = new MarkdownWriter();
        writer.writeStatistics(report, dir);

        assertThat(Files.readString(Path.of(dir.toString(), "logStats.md"))).isEqualTo(expected);
    }

}
