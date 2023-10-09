package edu.hw1.KnightsOnBoard;

import static edu.hw1.KnightsOnBoard.KnightChecker.checkKnight;

public final class KnightsOnBoard {
    private KnightsOnBoard() {
    }

    private static final int BOARD_SIZE = 8;
    private static final String WRONG_FORMAT_MESSAGE = "Wrong board format";

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            throw new NullPointerException("Board is null");
        }
        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException(WRONG_FORMAT_MESSAGE);
        }

        for (int[] row : board) {
            if (row == null) {
                throw new NullPointerException("Board has null row");
            }
            if (row.length != BOARD_SIZE) {
                throw new IllegalArgumentException(WRONG_FORMAT_MESSAGE);
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 1 && !checkKnight(i, j, board, BOARD_SIZE)) {
                    return false;
                }
            }
        }

        return true;
    }
}
