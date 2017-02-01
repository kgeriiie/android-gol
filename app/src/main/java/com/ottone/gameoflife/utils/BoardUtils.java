package com.ottone.gameoflife.utils;

import com.ottone.gameoflife.bo.CellState;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class BoardUtils {

    public static int countNeighbourCells(int x, int y, int[][] board) {

        int count = 0;
        int current = 0;

        boolean LEFT_CONDITION = (0 <= (x - 1));
        boolean RIGHT_CONDITION = (board[x].length -1 >= (x + 1));

        // top
        if (0 <= (y - 1)) {
            // top-left
            current = board[Math.max(0, (y - 1))][Math.max(0, (x - 1))];
            count += LEFT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;

            // top
            current = board[Math.max(0, (y - 1))][x];
            count += (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;

            // top-right
            current = board[Math.max(0, (y - 1))][Math.min(board[x].length -1, (x + 1))];
            count += RIGHT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;
        }

        // left
        current = board[y][Math.max(0, (x - 1))];
        count +=     LEFT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;

        // right
        current = board[y][Math.min(board[x].length -1, (x + 1))];
        count +=     RIGHT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;

        // bottom
        if ((board[x].length -1) >= (y + 1)) {
            // bottom-left
            current = board[Math.min(board[y].length -1, (y + 1))][Math.max(0, (x - 1))];
            count += LEFT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD)  ? 1 : 0;

            // bottom
            current = board[Math.min(board[y].length -1, (y + 1))][x];
            count += (current == CellState.LIVE || current == CellState.DEAD) ? 1 : 0;

            // bottom-right
            current = board[Math.min(board[y].length -1, (y + 1))][Math.min(board[x].length -1, (x + 1))];
            count += RIGHT_CONDITION && (current == CellState.LIVE || current == CellState.DEAD)  ? 1 : 0;
        }

        return count;
    }
}
