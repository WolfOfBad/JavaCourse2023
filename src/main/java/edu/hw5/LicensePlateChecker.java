package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LicensePlateChecker {
    private static final String CHARACTERS = "[АВЕКМНОРСТУХ]";

    public boolean check(String plate) {
        Pattern pattern = Pattern.compile("^" + CHARACTERS + "\\d{3}" + CHARACTERS + "{2}\\d{2,3}$");
        Matcher matcher = pattern.matcher(plate);
        return matcher.find();
    }
}
