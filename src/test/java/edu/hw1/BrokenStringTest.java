package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.BrokenString.fixString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrokenStringTest {
    @ParameterizedTest(name = "#{index} -> Broken string = {0}, Fixed String = {1}")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde"
    })
    public void fixStringShouldWorkGivenDifferentArguments(String brokenString, String expectedString) {
        String fixedString = fixString(brokenString);

        assertThat(fixedString).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("Проверка строки на null")
    public void nullTest() {
        // given
        String broken = null;

        // when
        Exception thrown = assertThrows(Exception.class, () -> fixString(broken));

        // then
        assertThat(thrown).hasMessage("Broken string is null");
    }

}
