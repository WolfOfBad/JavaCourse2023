package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class BracketsClusterizer {
    public List<String> clusterize(@NotNull String string) {
        if (string.isEmpty() || string.charAt(0) != '(') {
            return null;
        }

        int bracketsCount = 0;
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        for (char ch : string.toCharArray()) {
            sb.append(ch);

            if (ch == '(') {
                bracketsCount++;
            } else if (ch == ')') {
                bracketsCount--;
            }

            if (bracketsCount == 0) {
                result.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (bracketsCount < 0) {
                return null;
            }
        }

        if (!sb.isEmpty()) {
            return null;
        }

        return result;
    }

}
