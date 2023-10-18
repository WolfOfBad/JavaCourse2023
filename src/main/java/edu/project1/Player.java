package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {
    private final Logger logger = LogManager.getLogger();
    private final List<Character> guessedCharacters = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private boolean pressedInterrupt = false;

    private String checkInterruption() {
        String result = "interrupted";
        try {
            result = scanner.next().toLowerCase();
        } catch (Exception e) {
            pressedInterrupt = true;
        }
        return result;
    }

    public boolean isPressedInterrupt() {
        return pressedInterrupt;
    }

    public boolean continueGame() {
        logger.info("Do you want to continue game? y/n");
        String answer = checkInterruption();
        while (!pressedInterrupt
            && (answer.length() != 1
            || (answer.charAt(0) != 'y' && answer.charAt(0) != 'n'))) {
            logger.info("Please enter y/n for continue game or to exit");
            answer = checkInterruption();
        }
        if (answer.charAt(0) == 'y') {
            guessedCharacters.clear();
            return true;
        } else {
            return false;
        }
    }

    public char getCharacter() {
        logger.info("Guess a letter:");
        String answer = checkInterruption();
        while (!pressedInterrupt && !checkGuessing(answer)) {
            answer = checkInterruption();
        }
        guessedCharacters.add(answer.charAt(0));
        return answer.charAt(0);
    }

    private boolean checkGuessing(String answer) {
        if (answer.length() != 1) {
            logger.info("Please enter only one character to guess");
            return false;
        }
        if (guessedCharacters.contains(answer.charAt(0))) {
            logger.info("You already entered this character. Try to guess other letter");
            return false;
        }
        return true;
    }
}
