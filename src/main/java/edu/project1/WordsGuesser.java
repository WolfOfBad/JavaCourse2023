package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordsGuesser {
    private String word;
    private String guessedWord;
    Logger logger = LogManager.getLogger();

    public void setWord(String word) {
        this.word = word;
        StringBuilder stringBuilder = new StringBuilder();
        while (word.length() != stringBuilder.length()) {
            stringBuilder.append('*');
        }
        guessedWord = stringBuilder.toString();
    }

    public String getWord() {
        return word;
    }

    public void printCurrentState() {
        logger.info("The word: " + guessedWord);
    }

    public boolean guessedAllWord() {
        return !guessedWord.contains("*");
    }

    public boolean guessCharacter(char character) {
        if (!word.contains(Character.toString(character))) {
            logger.info("Missed!");
            return false;
        }
        logger.info("Hit!");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == character) {
                stringBuilder.append(character);
            } else {
                stringBuilder.append(guessedWord.charAt(i));
            }
        }
        guessedWord = stringBuilder.toString();
        return true;
    }
}
