package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class AtbashCipher {
    public String encode(@NotNull String string) {
        StringBuilder sb = new StringBuilder();

        for (char ch : string.toCharArray()) {
            if ('a' <= ch && ch <= 'z') {
                sb.append((char) ('z' - (ch - 'a')));
            } else if ('A' <= ch && ch <= 'Z') {
                sb.append((char) ('Z' - (ch - 'A')));
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}
