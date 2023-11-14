package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Fridays13Finder {
    @SuppressWarnings("MagicNumber")
    public List<LocalDate> findFridaysInYear(Year year) {
        List<LocalDate> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            LocalDate date = LocalDate.of(year.getValue(), i, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                result.add(date);
            }
        }

        return result;
    }

    /**
     * Ищет следующую пятницу 13, включая переданную дату
     */
    @SuppressWarnings("MagicNumber")
    public LocalDate findNextFriday(LocalDate date) {
        LocalDate result = LocalDate.from(date).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        while (result.getDayOfMonth() != 13) {
            result = result.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return result;
    }
}
