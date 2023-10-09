package edu.hw1;

public final class BrokenString {
    private BrokenString() {
    }

    public static String fixString(String broken) {
        if (broken == null) {
            throw new NullPointerException("Broken string is null");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < broken.length() - 1; i += 2) {
            sb.append(broken.charAt(i + 1));
            sb.append(broken.charAt(i));
        }

        if (broken.length() % 2 == 1) {
            sb.append(broken.charAt(broken.length() - 1));
        }

        return sb.toString();
    }
}
