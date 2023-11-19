package edu.project3.Parsers.LogParser;

import edu.project3.Parsers.ArgumentsParser.UserArguments;
import edu.project3.Parsers.TimeParsers.TimeParsersChain;
import edu.project3.Statistics.Records.HTTPCode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {
    private static final Pattern NGINX_LOG_PATTERN = Pattern.compile(
        "^([^ ]+) - ([^ ]+) \\[([^]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^:])\" \"([^\"]+)\"$");
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
            lines.map(line -> parseLine(line, file))
                .filter(Objects::nonNull)
                .filter(log -> log.time().isEqual(from)
                    || log.time().isEqual(to)
                    || log.time().isAfter(from) && log.time().isBefore(to))
                .forEach(result::add);
        }
    }

    @SuppressWarnings("MagicNumber")
    private LogRecord parseLine(String line, Path file) {
        Matcher matcher = NGINX_LOG_PATTERN.matcher(line);
        if (matcher.find()) {
            return new LogRecord(
                matcher.group(1),
                matcher.group(2),
                TimeParsersChain.parse(matcher.group(3)),
                matcher.group(4),
                matcher.group(4).split(" ")[1],
                new HTTPCode(Integer.parseInt(matcher.group(5))),
                Long.parseLong(matcher.group(6)),
                matcher.group(7),
                matcher.group(8),
                file
            );
        }
        return null;
    }

}
