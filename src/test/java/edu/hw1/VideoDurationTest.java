package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.VideoDuration.minutesToSeconds;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VideoDurationTest {
    @ParameterizedTest(name = "#{index} -> Time string = {0}, Expected result = {1}")
    @CsvSource({
        "01:00, 60",
        "13:56, 836",
        "78:56, 4736",
        "10:60, -1",
        "17:13:40, -1",
        "15:333, -1",
        "1:00001, -1"
    })
    @DisplayName("Тест длины видео на разных значениях")
    public void minutesToSecondsShouldWorkDifferentArguments(String time, int expectedResult) {
        int result = minutesToSeconds(time);

        assertThat(result).isEqualTo(expectedResult);
    }
}
