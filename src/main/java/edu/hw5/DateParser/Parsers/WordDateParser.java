package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <p>Обрабатывает строки</p>
 * <p>tomorrow</p>
 * <p>today</p>
 * <p>yesterday</p>
 */
public class WordDateParser extends DateParser {
    @Override
    public Optional<LocalDate> parseDate(String date) {
        if (date.toLowerCase().compareTo("tomorrow") == 0) {
            return Optional.of(LocalDate.now().plusDays(1));
        }
        if (date.toLowerCase().compareTo("today") == 0) {
            return Optional.of(LocalDate.now().plusDays(0));
        }
        if (date.toLowerCase().compareTo("yesterday") == 0) {
            return Optional.of(LocalDate.now().minusDays(1));
        }

        return parseNext(date);
    }
}
