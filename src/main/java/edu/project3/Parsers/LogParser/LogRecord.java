package edu.project3.Parsers.LogParser;

import edu.project3.Parsers.TimeParsers.TimeParsersChain;
import edu.project3.Statistics.Records.HTTPCode;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record LogRecord(String ip,
                        String user,
                        OffsetDateTime time,
                        String request,
                        String resource,
                        HTTPCode code,
                        long bytesSent,
                        String httpReferer,
                        String httpUserAgent,
                        Path file) {
    private static final Pattern NGINX_LOG_PATTERN = Pattern.compile(
        "^([^ ]+) - ([^ ]+) \\[([^]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^:])\" \"([^\"]+)\"$");

    @SuppressWarnings("MagicNumber")
    public static LogRecord parse(String line, Path file) {
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
