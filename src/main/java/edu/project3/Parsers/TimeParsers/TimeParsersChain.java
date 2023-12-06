package edu.project3.Parsers.TimeParsers;

import edu.project3.Parsers.TimeParsers.Parsers.AbstractTimeParser;
import edu.project3.Parsers.TimeParsers.Parsers.NGINXTimeParser;
import edu.project3.Parsers.TimeParsers.Parsers.YYYYMMDDParser;
import java.time.OffsetDateTime;

public final class TimeParsersChain {
    private static final AbstractTimeParser PARSERS = AbstractTimeParser.link(
        new NGINXTimeParser(),
        new YYYYMMDDParser()
    );

    private TimeParsersChain() {
    }

    public static OffsetDateTime parse(String dateTime) {
        return PARSERS.parse(dateTime);
    }
}
