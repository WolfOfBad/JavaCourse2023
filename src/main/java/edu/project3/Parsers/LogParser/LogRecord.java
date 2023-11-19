package edu.project3.Parsers.LogParser;

import edu.project3.Statistics.Records.HTTPCode;
import java.nio.file.Path;
import java.time.OffsetDateTime;

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
}
