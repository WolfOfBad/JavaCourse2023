package edu.hw8.Bruteforce;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;

public class ParallelBruteforce implements Bruteforce {
    private static final int STEP = 100_000;
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private final int maxLength;
    private Set<String> passwords;
    private Map<String, String> result;
    private final ExecutorService pool;

    public ParallelBruteforce(int maxLength, int threads) {
        this.maxLength = maxLength;
        this.pool = Executors.newFixedThreadPool(threads);
    }

    @Override
    public Map<String, String> find(Map<String, String> hashPasswords) {
        passwords = new HashSet<>(hashPasswords.values());

        result = Collections.synchronizedMap(new HashMap<>());

        int maxPassword = (int) Math.pow(ALPHABET.length(), maxLength);

        /*int iterations = maxPassword / threads + threads;
        for (int i = 0; i < threads; i++) {
            pool.submit(getRunnable(i * iterations, (i + 1) * iterations));
        }*/

        for (int i = 0; i < maxPassword; i += STEP) {
            pool.submit(getRunnable(i, Math.min(i + STEP, maxPassword)));
        }

        pool.close();

        return hashPasswords
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                (entry) -> result.getOrDefault(entry.getValue(), "")
            ));
    }

    private Runnable getRunnable(int start, int end) {
        return () -> {
            for (int i = start; i < end; i++) {
                String password = Integer.toString(i, ALPHABET.length());
                while (password.length() <= maxLength) {
                    String hash = DigestUtils.md5Hex(password);
                    if (passwords.contains(hash)) {
                        result.put(hash, password);
                    }
                    password = ALPHABET.charAt(0) + password;
                }
            }
        };
    }
}
