package edu.project3.Parsers.TimeParsers.Parsers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class NGINXTimeParser extends AbstractTimeParser {
    private final DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("dd/MMM/yyyy:HH:mm:ss X").localizedBy(Locale.ENGLISH);

    @Override
    public OffsetDateTime parse(String dateTime) {
        try {
            return OffsetDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return parseNext(dateTime);
        }
    }
}
