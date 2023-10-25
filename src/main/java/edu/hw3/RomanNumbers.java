package edu.hw3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanNumbers {
    @SuppressWarnings("MagicNumber")
    private static final Map<Integer, String> NUMBERS_MAP = Stream.of(new Object[][] {
        {1000, "M"},
        {900, "CM"},
        {500, "D"},
        {400, "CD"},
        {100, "C"},
        {90, "XC"},
        {50, "L"},
        {40, "XL"},
        {10, "X"},
        {9, "IX"},
        {5, "V"},
        {4, "IV"},
        {1, "I"}
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
    private static final List<Integer> NUMBERS = NUMBERS_MAP
        .keySet()
        .stream()
        .sorted()
        .toList()
        .reversed();
    private static final int MAX_VALUE = 3999;

    public String convertToRoman(int number) {
        if (number > MAX_VALUE) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int num = number;
        for (int arabic : NUMBERS) {
            while (num >= arabic) {
                sb.append(NUMBERS_MAP.get(arabic));
                num -= arabic;
            }
        }

        return sb.toString();
    }

}
