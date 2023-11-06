package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateParser extends DateParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        // tomorrow
        // today
        // yesterday
        if (string.toLowerCase().compareTo("tomorrow") == 0) {
            return Optional.of(LocalDate.now().plusDays(1));
        }
        if (string.toLowerCase().compareTo("today") == 0) {
            return Optional.of(LocalDate.now().plusDays(0));
        }
        if (string.toLowerCase().compareTo("yesterday") == 0) {
            return Optional.of(LocalDate.now().minusDays(1));
        }

        return parseNext(string);
    }
}
