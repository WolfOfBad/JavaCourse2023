package edu.hw1;

public final class CyclicShift {
    private CyclicShift() {
    }

    private static final String NEGATIVE_NUMBER_EXCEPTION_MESSAGE = "You cannot use negative numbers";

    public static int rotateLeft(int n, int shift) {
        if (n < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION_MESSAGE);
        }

        String bitStr = Integer.toBinaryString(n);
        int result = 0;
        int pow = bitStr.length() - 1;

        for (int i = 0; i < bitStr.length(); i++) {
            if (bitStr.charAt((i + shift) % bitStr.length()) == '1') {
                result += (int) Math.pow(2, pow);
            }
            pow--;
        }

        return result;
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION_MESSAGE);
        }

        String bitStr = Integer.toBinaryString(n);
        int fixedShift = shift % bitStr.length();
        int result = 0;
        int pow = bitStr.length() - 1;

        for (int i = 0; i < bitStr.length(); i++) {
            if (bitStr.charAt((bitStr.length() + i - fixedShift) % bitStr.length()) == '1') {
                result += (int) Math.pow(2, pow);
            }
            pow--;
        }

        return result;
    }
}
