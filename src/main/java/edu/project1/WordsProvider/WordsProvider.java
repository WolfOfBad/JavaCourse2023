package edu.project1.WordsProvider;

public interface WordsProvider {
    // Можно имплементировать различные реализации получения слов для игры, например считывать из файла
    String getWord();

    @SuppressWarnings("MagicNumber")
    default boolean checkWord(String word) {
        return word.length() >= 4 && word.length() <= 15;
    }
}
