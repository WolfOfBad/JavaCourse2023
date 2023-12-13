package edu.hw8.ToxicResponseServer.Server.PhraseFinder;

import java.util.List;

public class SimplePhraseFinder implements PhraseFinder {
    private static final int SLEEP_TIME = 1000;

    private final List<String> phrases = List.of(
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма",
        "Чем ниже интеллект, тем громче оскорбления"
    );

    @Override
    public String find(String word) {
        try {
            // Emulation of search process
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (String phrase : phrases) {
            if (phrase.contains(word)) {
                return phrase;
            }
        }
        return "Невозможно найти фразу";
    }
}
