package edu.project1;

import edu.project1.WordsProvider.SimpleWordsProvider;
import edu.project1.WordsProvider.WordsProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HangmanConsole {
    private final WordsProvider wordsProvider = new SimpleWordsProvider();
    private final WordsGuesser wordsGuesser = new WordsGuesser();
    private final Player player = new Player();
    private final Logger logger = LogManager.getLogger();
    private final static int MAX_ERRORS = 5;

    public void run() {
        logger.info("Welcome to console game Hangman.\n");

        do {
            wordsGuesser.setWord(wordsProvider.getWord());
            int errors = 0;
            while (errors < MAX_ERRORS && !wordsGuesser.guessedAllWord()) {
                wordsGuesser.getCurrentState();
                char guessed = player.getCharacter();
                if (!wordsGuesser.guessCharacter(guessed)) {
                    errors++;
                    logger.info("Mistake " + errors + " out of " + MAX_ERRORS);
                }
            }
            if (errors == MAX_ERRORS) {
                logger.info("You lost");
            } else {
                logger.info("You won");
            }
            logger.info("The word was " + wordsGuesser.getWord() + "\n");

        } while (player.continueGame());

    }

}
