package edu.hw8.Bruteforce;

import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;

public class SimpleBruteforce implements Bruteforce {
    private final int maxLength;
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    public SimpleBruteforce(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Map<String, String> find(Map<String, String> hashPasswords) {
        Map<String, String> passwords = hashPasswords
            .entrySet()
            .stream()
            .collect(Collectors
                .toMap(Map.Entry::getValue, (entry) -> ""));

        for (int i = 0; i < Math.pow(ALPHABET.length(), maxLength); i++) {
            String password = Integer.toString(i, ALPHABET.length());

            while (password.length() <= maxLength) {
                String hash = DigestUtils.md5Hex(password);
                if (passwords.containsKey(hash)) {
                    passwords.replace(hash, password);
                }
                password = ALPHABET.charAt(0) + password;
            }
        }

        return hashPasswords
            .entrySet()
            .stream()
            .collect(Collectors
                .toMap(Map.Entry::getKey, (entry) -> passwords.get(entry.getValue())));
    }
}
