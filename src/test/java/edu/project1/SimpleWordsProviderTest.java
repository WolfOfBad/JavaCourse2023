package edu.project1;

import edu.project1.WordsProvider.SimpleWordsProvider;
import edu.project1.WordsProvider.WordsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleWordsProviderTest {
    @Test
    @DisplayName("Тест простого поставщика слов")
    public void simpleProviderTest() {
        WordsProvider simpleWordsProvider = new SimpleWordsProvider();
        Set<String> words = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            words.add(simpleWordsProvider.getWord());
        }

        assertThat(words.size()).isEqualTo(5);
    }

}
