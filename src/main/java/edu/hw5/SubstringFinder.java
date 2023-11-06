package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringFinder {

    /**
     * Возвращает индекс первого вхождения подстроки в строку, иначе -1
     */
    public int find(String string, String substring) {
        Pattern pattern = Pattern.compile(substring);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }
}
