package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Обрабатывает строки типа</p>
 * <p>1/3/1976</p>
 * <p>1/3/20</p>
 */
public class SlashParser extends DateParser {
    @Override
    @SuppressWarnings("MagicNumber")
    public Optional<LocalDate> parseDate(String date) {
        Pattern fullYearPattern = Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{4})$");
        Matcher matcher = fullYearPattern.matcher(date);
        if (matcher.find()) {
            return Optional.of(LocalDate.of(
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(1))
            ));
        }

        Pattern shortYearPattern = Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{2})$");
        matcher = shortYearPattern.matcher(date);
        if (matcher.find()) {
            return Optional.of(LocalDate.of(
                Integer.parseInt(matcher.group(3)) + 2000,
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(1))
            ));
        }

        return parseNext(date);
    }
}
