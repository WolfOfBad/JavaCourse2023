package edu.hw5.DateParser.Parsers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NDaysAgoParser extends DateParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        // 1 day ago
        // 2234 days ago
        Pattern pattern = Pattern.compile("^(\\d+) days? ago$");
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
        }

        return parseNext(string);
    }
}
