package edu.hw1;

public final class SpecialPalindrome {
    private SpecialPalindrome() {
    }

    public static boolean isPalindromeDescendant(int number) {
        String num = Integer.toString(number);
        while (num.length() >= 2) {
            if (isPalindrome(num)) {
                return true;
            }
            num = findAncestor(num);
        }
        return false;
    }

    private static boolean isPalindrome(String number) {
        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static String findAncestor(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length() - 1; i += 2) {
            int sum = number.charAt(i) + number.charAt(i + 1) - '0' - '0';
            sb.append(sum);
        }
        if (number.length() % 2 == 1) {
            sb.append(number.charAt(number.length() - 1));
        }
        return sb.toString();
    }
}
