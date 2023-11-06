package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubstringFinderTest {
    @Test
    @DisplayName("Удачный поиск подстроки")
    public void successfulFindTest() {
        String string = "abcdefg";
        String substring = "cde";

        SubstringFinder obj = new SubstringFinder();
        int result = obj.find(string, substring);

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Неудачный поиск подстроки")
    public void unsuccessfulFindTest() {
        String string = "abcd";
        String substring = "abd";

        SubstringFinder obj = new SubstringFinder();
        int result = obj.find(string, substring);

        assertThat(result).isEqualTo(-1);
    }

}
