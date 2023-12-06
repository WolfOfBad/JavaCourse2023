package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogAnalyzerTest {
    private final String expected = """
        #### Общая информация
        |Метрика|Значение|
        |:------|-------:|
        |Файлы|logs.txt|
        |Начало логгирования|2015-05-28T02:05:56Z|
        |Конец логгирования|2015-05-28T02:05:56Z|
        |Количество запросов|1|
        |Средний размер ответа|336|
        #### Запрашиваемые ресурсы
        |Ресурс|Количество|
        |:-----|-------:|
        |/downloads/product_1|1|
        #### Коды ответа
        |Код|Имя|Количество|
        |:--|:-:|---------:|
        |404|Not Found|1|
        #### Запросы по дням
        |День|Количество запросов|
        |:---|------------------:|
        |2015-05-28|1|
        #### Запросы по часам
        |Час|Количество запросов|
        |:--|------------------:|
        |0|0|
        |1|0|
        |2|1|
        |3|0|
        |4|0|
        |5|0|
        |6|0|
        |7|0|
        |8|0|
        |9|0|
        |10|0|
        |11|0|
        |12|0|
        |13|0|
        |14|0|
        |15|0|
        |16|0|
        |17|0|
        |18|0|
        |19|0|
        |20|0|
        |21|0|
        |22|0|
        |23|0|
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

        assertThat(Files.readString(Path.of(dir.toString(), "logStats.md"))
            .replaceAll("\r\n", "\n")).isEqualTo(expected);

    }

}
