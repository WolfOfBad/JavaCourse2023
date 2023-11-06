package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    private DateParser nextParser;

    public static DateParser link(DateParser first, DateParser... chain) {
        DateParser head = first;
        for (DateParser next : chain) {
            head.nextParser = next;
            head = next;
        }
        return first;
    }

    public abstract Optional<LocalDate> parseDate(String string);

    protected Optional<LocalDate> parseNext(String string) {
        if (nextParser == null) {
            return Optional.empty();
        }
        return nextParser.parseDate(string);
    }
}
