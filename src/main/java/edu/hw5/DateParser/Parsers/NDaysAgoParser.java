package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Обрабатывает строки типа</p>
 * <p>1 day ago</p>
 * <p>2234 days ago</p>
 */
public class NDaysAgoParser extends DateParser {
    @Override
    public Optional<LocalDate> parseDate(String date) {
        Pattern pattern = Pattern.compile("^(\\d+) days? ago$");
        Matcher matcher = pattern.matcher(date);

        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
        }

        return parseNext(date);
    }
}
