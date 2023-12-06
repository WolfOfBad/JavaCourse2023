package edu.project3.Parsers.TimeParsers.Parsers;

import java.time.OffsetDateTime;

public abstract class AbstractTimeParser {
    private AbstractTimeParser next;

    public static AbstractTimeParser link(AbstractTimeParser first, AbstractTimeParser... chain) {
        AbstractTimeParser head = first;
        for (AbstractTimeParser next : chain) {
            head.next = next;
            head = next;
        }
        return first;
    }

    protected OffsetDateTime parseNext(String dateTime) {
        if (next == null) {
            return null;
        }
        return next.parse(dateTime);
    }

    public abstract OffsetDateTime parse(String dateTime);
}
