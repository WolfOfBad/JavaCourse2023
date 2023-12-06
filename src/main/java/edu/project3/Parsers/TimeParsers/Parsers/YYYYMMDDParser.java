package edu.project3.Parsers.TimeParsers.Parsers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

public class YYYYMMDDParser extends AbstractTimeParser {
    @Override
    public OffsetDateTime parse(String dateTime) {
        try {
            return OffsetDateTime.of(
                LocalDate.parse(dateTime),
                LocalTime.MIDNIGHT,
                ZoneOffset.UTC
            );
        } catch (DateTimeParseException e) {
            return parseNext(dateTime);
        }
    }
}
