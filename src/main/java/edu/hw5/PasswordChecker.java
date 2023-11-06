package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    public boolean check(String password) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
