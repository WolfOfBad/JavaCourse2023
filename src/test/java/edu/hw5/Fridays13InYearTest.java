package edu.hw5;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Fridays13InYearTest {

    @Test
    @DisplayName("Тест поиска пятниц в году")
    public void allFridaysTest() {
        Year year = Year.of(1925);

        Fridays13Finder obj = new Fridays13Finder();
        List<LocalDate> result = obj.findFridaysInYear(year);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(LocalDate.of(1925, 2, 13));
        assertThat(result.get(1)).isEqualTo(LocalDate.of(1925, 3, 13));
        assertThat(result.get(2)).isEqualTo(LocalDate.of(1925, 11, 13));
    }

    @Test
    @DisplayName("Тест поиска следующей пятницы")
    public void nextFridayTest() {
        LocalDate date = LocalDate.of(2023, 11, 6);

        Fridays13Finder obj = new Fridays13Finder();
        LocalDate result = obj.findNextFriday(date);

        assertThat(result).isEqualTo(LocalDate.of(2024, 9, 13));
    }

}
