package edu.project3.Parsers.LogParser;

import edu.project3.Parsers.ArgumentsParser.UserArguments;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LogParser {
    private List<LogRecord> result;

    public List<LogRecord> parse(UserArguments arguments) throws IOException {
        result = new ArrayList<>();

        for (Path file : arguments.files()) {
            parseFile(file, arguments.from(), arguments.to());
        }

        return result;
    }

    private void parseFile(Path file, OffsetDateTime from, OffsetDateTime to) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.map(line -> LogRecord.parse(line, file))
                .filter(Objects::nonNull)
                .filter(log -> log.time().isEqual(from)
                    || log.time().isEqual(to)
                    || log.time().isAfter(from) && log.time().isBefore(to))
                .forEach(result::add);
        }
    }
}
