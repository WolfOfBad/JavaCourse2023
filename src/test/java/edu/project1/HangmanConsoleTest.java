package edu.project1;

import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HangmanConsoleTest {
    @Test
    @DisplayName("Сценарий проигрыша игры")
    public void loseGameTest() throws Exception {
        WordsGuesser wordsGuesserMock = mock(WordsGuesser.class);
        Player playerMock = mock(Player.class);

        Mockito.when(wordsGuesserMock.guessCharacter('a')).thenReturn(false);
        Mockito.when(playerMock.getCharacter()).thenReturn('a');

        HangmanConsole game = new HangmanConsole();
        Field wordGuesserField = HangmanConsole.class.getDeclaredField("wordsGuesser");
        Field playerField = HangmanConsole.class.getDeclaredField("player");
        wordGuesserField.setAccessible(true);
        playerField.setAccessible(true);
        wordGuesserField.set(game, wordsGuesserMock);
        playerField.set(game, playerMock);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(HangmanConsole.class);

        game.run();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(2)).isEqualTo("Mistake 1 out of 5");
        assertThat(loggedStrings.get(3)).isEqualTo("Mistake 2 out of 5");
        assertThat(loggedStrings.get(4)).isEqualTo("Mistake 3 out of 5");
        assertThat(loggedStrings.get(5)).isEqualTo("Mistake 4 out of 5");
        assertThat(loggedStrings.get(6)).isEqualTo("Mistake 5 out of 5");
        assertThat(loggedStrings.get(7)).isEqualTo("You lost");
        assertThat(loggedStrings.get(8)).isEqualTo("The word was null\n");

    }

    @Test
    @DisplayName("Сценарий выигрыша игры")
    public void winGameTest() throws Exception {
        WordsGuesser wordsGuesserMock = mock(WordsGuesser.class);
        Player playerMock = mock(Player.class);

        Mockito.when(wordsGuesserMock.guessedAllWord()).thenReturn(true);
        Mockito.when(playerMock.continueGame()).thenReturn(false);

        HangmanConsole game = new HangmanConsole();
        Field wordGuesserField = HangmanConsole.class.getDeclaredField("wordsGuesser");
        Field playerField = HangmanConsole.class.getDeclaredField("player");
        wordGuesserField.setAccessible(true);
        playerField.setAccessible(true);
        wordGuesserField.set(game, wordsGuesserMock);
        playerField.set(game, playerMock);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(HangmanConsole.class);

        game.run();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(2)).isEqualTo("You won");
        assertThat(loggedStrings.get(3)).isEqualTo("The word was null\n");
    }

    @Test
    @DisplayName("Сценарий прерывания игры")
    public void interruptGameTest() throws Exception {
        Player playerMock = mock(Player.class);

        Mockito.when(playerMock.isPressedInterrupt()).thenReturn(true);

        HangmanConsole game = new HangmanConsole();
        Field playerField = HangmanConsole.class.getDeclaredField("player");
        playerField.setAccessible(true);
        playerField.set(game, playerMock);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(HangmanConsole.class);

        game.run();

        List<String> loggedStrings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(loggedStrings.get(2)).isEqualTo("You ended game by hotkey");
    }
}
