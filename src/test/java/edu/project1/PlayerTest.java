package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("Тест ввода игрока")
    public void guessingTest() throws Exception {
        Player player = new Player();
        InputStream bs = new ByteArrayInputStream(("""
            abc
            a
            b
            b
            c
            """).getBytes());

        Field field = Player.class.getDeclaredField("scanner");
        field.setAccessible(true);
        field.set(player, new Scanner(bs));

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(Player.class);
        for (int i = 0; i < 3; i++) {
            player.getCharacter();
        }

        List<String> loggedStrings = loggerStringsGetter.getStrings();

        assertThat(loggedStrings.get(0)).isEqualTo("Guess a letter:");
        assertThat(loggedStrings.get(1)).isEqualTo("Please enter only one character to guess");
        assertThat(loggedStrings.get(2)).isEqualTo("Guess a letter:");
        assertThat(loggedStrings.get(3)).isEqualTo("Guess a letter:");
        assertThat(loggedStrings.get(4)).isEqualTo("You already entered this character. Try to guess other letter");
    }

    static Arguments[] continueArguments() {
        return new Arguments[] {
            Arguments.of("y", true),
            Arguments.of("n", false),
            Arguments.of("x\ny", true),
            Arguments.of("x\nn", false),
            Arguments.of("abc\ny", true),
            Arguments.of("abc\nn", false),
        };
    }

    @ParameterizedTest
    @MethodSource("continueArguments")
    @DisplayName("Тест продолжения игры")
    public void continueGameTest(String input, boolean result) throws Exception {
        Player player = new Player();
        InputStream bs = new ByteArrayInputStream(input.getBytes());

        Field field = Player.class.getDeclaredField("scanner");
        field.setAccessible(true);
        field.set(player, new Scanner(bs));

        assertThat(player.continueGame()).isEqualTo(result);
    }

    @Test
    @DisplayName("Тест горячей клавиши прерывания")
    public void interruptTest() throws Exception {
        Player player = new Player();
        InputStream bs = new ByteArrayInputStream("".getBytes());

        Field field = Player.class.getDeclaredField("scanner");
        field.setAccessible(true);
        field.set(player, new Scanner(bs));

        player.getCharacter();

        assertThat(player.isPressedInterrupt()).isTrue();
    }

}
