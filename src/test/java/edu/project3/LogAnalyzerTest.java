package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogAnalyzerTest {
    private final String expected = """
        #### Общая информация\r
        |Метрика|Значение|\r
        |:------|-------:|\r
        |Файлы|logs.txt|\r
        |Начало логгирования|2015-05-28T02:05:56Z|\r
        |Конец логгирования|2015-05-28T02:05:56Z|\r
        |Количество запросов|1|\r
        |Средний размер ответа|336|\r
        #### Запрашиваемые ресурсы\r
        |Ресурс|Количество|\r
        |:-----|-------:|\r
        |/downloads/product_1|1|\r
        #### Коды ответа\r
        |Код|Имя|Количество|\r
        |:--|:-:|---------:|\r
        |404|Not Found|1|\r
        #### Запросы по дням\r
        |День|Количество запросов|\r
        |:---|------------------:|\r
        |2015-05-28|1|\r
        #### Запросы по часам\r
        |Час|Количество запросов|\r
        |:--|------------------:|\r
        |0|0|\r
        |1|0|\r
        |2|1|\r
        |3|0|\r
        |4|0|\r
        |5|0|\r
        |6|0|\r
        |7|0|\r
        |8|0|\r
        |9|0|\r
        |10|0|\r
        |11|0|\r
        |12|0|\r
        |13|0|\r
        |14|0|\r
        |15|0|\r
        |16|0|\r
        |17|0|\r
        |18|0|\r
        |19|0|\r
        |20|0|\r
        |21|0|\r
        |22|0|\r
        |23|0|\r
        """;

    @Test
    public void analyzerTest(@TempDir Path dir) throws IOException {
        String[] args = new String[] {
            "--path", "src/test/resources/project3/logs.txt",
            "--format", "markdown",
            "--from", "2015-05-20",
            "--to", "2015-06-01",
            "--output", dir.toString(),
        };

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze(args);

        assertThat(Files.readString(Path.of(dir.toString(), "logStats.md"))).isEqualTo(expected);

    }

}
