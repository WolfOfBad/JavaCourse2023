package edu.hw5.DateParser;

import edu.hw5.DateParser.Parsers.DateParser;
import edu.hw5.DateParser.Parsers.NDaysAgoParser;
import edu.hw5.DateParser.Parsers.SlashParser;
import edu.hw5.DateParser.Parsers.WordDateParser;
import edu.hw5.DateParser.Parsers.YearMonthDayParser;
import java.time.LocalDate;
import java.util.Optional;

public class ChainDateParser {
    private static final DateParser PARSER = DateParser.link(
        new YearMonthDayParser(),
        new SlashParser(),
        new WordDateParser(),
        new NDaysAgoParser()
    );

    public Optional<LocalDate> parseDate(String string) {
        return PARSER.parseDate(string);
    }
}
