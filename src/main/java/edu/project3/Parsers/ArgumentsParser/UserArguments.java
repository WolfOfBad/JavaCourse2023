package edu.project3.Parsers.ArgumentsParser;

import edu.project3.Output.StatisticsWriter;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;

public record UserArguments(
    List<Path> files,
    OffsetDateTime from,
    OffsetDateTime to,
    StatisticsWriter writer,
    Path output) {
}
