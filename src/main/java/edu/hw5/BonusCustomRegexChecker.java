package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BonusCustomRegexChecker {
    public boolean task1(String string) {
        Pattern pattern = Pattern.compile("^([01][01])*[01]$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public boolean task2(String string) {
        Pattern pattern = Pattern.compile("^0([01][01])*$|^1([01][01])*[01]$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public boolean task3(String string) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public boolean task4(String string) {
        Pattern pattern = Pattern.compile("^[01]?$|^0[01]*$|^10[01]*$|^110[01]*$|^111[01]+$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Задание 5, если считать символы в строке с 0
     */
    public boolean task5CountFrom0(String string) {
        Pattern pattern = Pattern.compile("^([01]1)*[01]?$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Задание 5, если считать символы в строке с 1
     */
    public boolean task5CountFrom1(String string) {
        Pattern pattern = Pattern.compile("^(1[01])*1?$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public boolean task6(String string) {
        Pattern pattern = Pattern.compile("^0{2,}$|^10{2,}$|^0+10+$|^0{2,}1$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    public boolean task7(String string) {
        Pattern pattern = Pattern.compile("^0*$|^1?(0+1)*0*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
