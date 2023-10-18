package edu.project1;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordsGuesserTest {
    @Test
    @DisplayName("Проверка геттера и сеттера слова")
    public void wordGetSetTest() {
        WordsGuesser wordsGuesser = new WordsGuesser();

        String word = "some word";

        wordsGuesser.setWord(word);

        assertThat(wordsGuesser.getWord()).isEqualTo(word);
    }

    @Test
    @DisplayName("Проверка вывода текущего состояния")
    public void currentStateTest() {
        WordsGuesser wordsGuesser = new WordsGuesser();
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(WordsGuesser.class);

        wordsGuesser.setWord("some word");
        wordsGuesser.getCurrentState();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(0)).isEqualTo("The word: *********");
    }

    @Test
    @DisplayName("Проверка угадывания символов")
    public void guessingCharactersTest() {
        WordsGuesser wordsGuesser = new WordsGuesser();
        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(WordsGuesser.class);

        wordsGuesser.setWord("worrd");

        boolean res1 = wordsGuesser.guessCharacter('w');
        boolean guessed1 = wordsGuesser.guessedAllWord();
        boolean res2 = wordsGuesser.guessCharacter('o');
        boolean guessed2 = wordsGuesser.guessedAllWord();
        boolean res3 = wordsGuesser.guessCharacter('a');
        boolean guessed3 = wordsGuesser.guessedAllWord();
        boolean res4 = wordsGuesser.guessCharacter('r');
        boolean guessed4 = wordsGuesser.guessedAllWord();
        boolean res5 = wordsGuesser.guessCharacter('d');
        boolean guessed5 = wordsGuesser.guessedAllWord();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(res1).isTrue();
        assertThat(res2).isTrue();
        assertThat(res3).isFalse();
        assertThat(res4).isTrue();
        assertThat(res5).isTrue();
        assertThat(guessed1).isFalse();
        assertThat(guessed2).isFalse();
        assertThat(guessed3).isFalse();
        assertThat(guessed4).isFalse();
        assertThat(guessed5).isTrue();
        assertThat(loggedStrings.get(0)).isEqualTo("Hit!");
        assertThat(loggedStrings.get(1)).isEqualTo("Hit!");
        assertThat(loggedStrings.get(2)).isEqualTo("Missed!");
        assertThat(loggedStrings.get(3)).isEqualTo("Hit!");
        assertThat(loggedStrings.get(4)).isEqualTo("Hit!");

    }

}
