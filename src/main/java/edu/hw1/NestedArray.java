package edu.hw1;

public final class NestedArray {
    private NestedArray() {
    }

    public static boolean isNestable(int[] first, int[] second) {
        if (first == null) {
            throw new NullPointerException("First array is null");
        }
        if (second == null) {
            throw new NullPointerException("Second array is null");
        }

        return findMin(first) > findMin(second) && findMax(first) < findMax(second);
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int num : array) {
            if (min > num) {
                min = num;
            }
        }
        return min;
    }

    private static int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }
}
