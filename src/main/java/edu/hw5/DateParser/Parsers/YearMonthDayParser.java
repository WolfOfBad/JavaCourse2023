package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearMonthDayParser extends DateParser {
    @Override
    @SuppressWarnings("MagicNumber")
    public Optional<LocalDate> parseDate(String string) {
        // Обрабатывает строки типа
        // 2020-10-10
        // 2020-12-2
        Pattern pattern = Pattern.compile("^(\\d{4})-(\\d{1,2})-(\\d{1,2})$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return Optional.of(LocalDate.of(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
            ));
        }

        return parseNext(string);
    }
}
