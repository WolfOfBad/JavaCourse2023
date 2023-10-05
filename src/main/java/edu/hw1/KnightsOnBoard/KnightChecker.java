package edu.hw1.KnightsOnBoard;

public final class KnightChecker {
    private KnightChecker() {
    }

    private static int boardSize;
    private static final int[][] DIRECTIONS = {
        {-2, -1},
        {-2, 1},
        {2, -1},
        {2, 1},
        {-1, -2},
        {-1, 2},
        {1, -2},
        {1, 2}
    };

    public static boolean checkKnight(int y, int x, int[][] board, int boardSize) {
        KnightChecker.boardSize = boardSize;
        boolean result = true;
        for (int[] direction : DIRECTIONS) {
            result = result && checkDirection(y, x, board, direction);
        }
        return result;
    }

    private static boolean checkDirection(int y, int x, int[][] board, int[] direction) {
        int movedY = y + direction[0];
        int movedX = x + direction[1];

        return movedY < 0 || movedY >= boardSize
            || movedX < 0 || movedX >= boardSize
            || board[movedY][movedX] != 1;
    }

}
