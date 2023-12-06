package edu.project3;

import edu.project3.Output.ADOCWriter;
import edu.project3.Parsers.ArgumentsParser.ArgumentsParser;
import edu.project3.Parsers.ArgumentsParser.UserArguments;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ArgumentsParserTest {
    @Test
    @DisplayName("Тест чтения из одного файла")
    public void simpleFileTest() throws IOException, InterruptedException {
        String[] args = new String[] {
            "--path",
            "/src/test/resources/project3/logs.txt",
        };

        ArgumentsParser parser = new ArgumentsParser(null);
        UserArguments result = parser.parseArgs(args);

        assertThat(result.files().size()).isEqualTo(1);
        assertThat(result.files().get(0)).isEqualTo(Path.of("/src/test/resources/project3/logs.txt"));
    }

    @Test
    @DisplayName("Тест чтения из нескольких файлов")
    public void globFileTest() throws IOException, InterruptedException {
        String[] args = new String[] {
            "--path",
            "src/test/resources/project3/**.txt",
        };

        ArgumentsParser parser = new ArgumentsParser(null);
        UserArguments result = parser.parseArgs(args);

        assertThat(result.files().size()).isEqualTo(2);
        assertThat(result.files().contains(Path.of("src/test/resources/project3/logs.txt"))).isTrue();
        assertThat(result.files().contains(Path.of("src/test/resources/project3/globtest/logs.txt"))).isTrue();
    }

    @Mock
    private HttpResponse<String> mockResponse;

    @Test
    @DisplayName("Тест получения файла по URL")
    public void URLFileTest() throws IOException, InterruptedException {
        HttpClient mockClient = mock(HttpClient.class);
        doReturn("test").when(mockResponse).body();
        doReturn(mockResponse).when(mockClient).send(any(), any());
        String[] args = new String[] {
            "--path",
            "https://someFile.com/file"
        };

        ArgumentsParser parser = new ArgumentsParser(mockClient);
        UserArguments result = parser.parseArgs(args);

        assertThat(Files.readString(result.files().get(0))).isEqualTo("test");
    }

    @Test
    @DisplayName("Тест дополнительных параметров")
    public void optionalArgsTest() throws IOException, InterruptedException {
        String[] args = new String[] {
            "--path", "/src/test/resources/project3/logs.txt",
            "--format", "adoc",
            "--from", "2023-11-10",
            "--to", "2023-11-20",
            "--output", "/src/test/resources/project3/"
        };

        ArgumentsParser parser = new ArgumentsParser(null);
        UserArguments result = parser.parseArgs(args);

        assertThat(result.from()).isEqualTo(OffsetDateTime.of(
            2023, 11, 10, 0, 0, 0, 0, ZoneOffset.UTC));
        assertThat(result.to()).isEqualTo(OffsetDateTime.of(
            2023, 11, 21, 0, 0, 0, 0, ZoneOffset.UTC));
        assertThat(result.output()).isEqualTo(Path.of("/src/test/resources/project3/"));
        assertThat(result.writer() instanceof ADOCWriter).isTrue();
    }
}
