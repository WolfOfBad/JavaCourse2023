package edu.project1.WordsProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimpleWordsProvider implements WordsProvider {
    private final String[] dictionary = new String[] {
        "hello",
        "world",
        "java",
        "programming",
        "tinkoff",
        "xd" // это слово не будет использоваться из за некорректной длины
    };
    private final List<String> correctWords;
    private List<String> remainWords;
    private final Random random = new Random();

    public SimpleWordsProvider() {
        correctWords = Arrays.stream(dictionary).filter(this::checkWord).toList();
        if (correctWords.isEmpty()) {
            throw new RuntimeException("Dictionary contains only incorrect words");
        }
        shuffleWords();
    }

    private void shuffleWords() {
        remainWords = new ArrayList<>(correctWords);

        for (int i = correctWords.size() - 1; i > 0; i--) {
            int index = random.nextInt(0, i);
            String temp = remainWords.get(i);
            remainWords.set(i, remainWords.get(index));
            remainWords.set(index, temp);
        }
    }

    @Override
    public String getWord() {
        if (remainWords.isEmpty()) {
            shuffleWords();
        }

        String result = remainWords.get(0);
        remainWords.remove(0);
        return result;
    }
}
