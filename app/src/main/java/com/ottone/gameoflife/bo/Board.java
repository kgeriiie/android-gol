package com.ottone.gameoflife.bo;

import android.util.Log;

import com.ottone.gameoflife.utils.BoardUtils;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class Board {

    private int mNumColumns, mNumRows;
    private int[][] mHabitatBoard;

    public Board(int pNumColumns, int pNumRows) {
        mNumColumns = pNumColumns;
        mNumRows = pNumRows;

        mHabitatBoard = new int[mNumRows][mNumColumns];
    }

    public int getColumnsCount() {
        return mNumColumns;
    }

    public int getRowsCount() {
        return mNumRows;
    }

    public int getCellStateOfBoard(int x, int y) {
        return mHabitatBoard[y][x];
    }

    public void setCellStateOfBoard(int x, int y, int state) {
        mHabitatBoard[y][x] = state;
    }

    public void addLife(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < mHabitatBoard.length && yCoordinate < mHabitatBoard[xCoordinate].length)
            mHabitatBoard[yCoordinate][xCoordinate] = CellState.LIVE;
    }

    public void applyRules() {
        for (int i = 0; i < mHabitatBoard.length; i++) {
            for (int j = 0; j < mHabitatBoard[i].length; j++) {
                setCellStateOfBoard(i,j, getNewStateOfCellWithNotations(i,j));
            }
        }

        eliminateNotations();
    }

    private void eliminateNotations() {
        for (int i = 0; i < mHabitatBoard.length; i++) {
            for (int j = 0; j < mHabitatBoard[i].length; j++) {
                setCellStateOfBoard(i,j, getNewStateOfCellWithoutNotations(i,j));
            }
        }
    }

    public int getNewStateOfCellWithNotations(int x, int y) {

        int count = BoardUtils.countNeighbourCells(x,y,mHabitatBoard);

        if (getCellStateOfBoard(x,y) == CellState.LIVE && (count < 2 || count > 3)) {
            return CellState.DEAD;
        } else if (getCellStateOfBoard(x,y) == CellState.EMPTY && count == 3) {
            return CellState.BIRTH;
        }

        return getCellStateOfBoard(x,y);
    }

    public int getNewStateOfCellWithoutNotations(int x, int y) {

        int state = getCellStateOfBoard(x,y);

        switch (state) {
            case CellState.BIRTH:
            case CellState.LIVE:
                return CellState.LIVE;
            case CellState.EMPTY:
            case CellState.DEAD:
                return CellState.EMPTY;
        }

        return CellState.EMPTY;
    }

    public int[][] getBoard() {
        return mHabitatBoard;
    }

    public void show() {
        for (int i = 0; i < mHabitatBoard.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < mHabitatBoard[i].length; j++) {
                sb.append(String.format("%d,",mHabitatBoard[i][j]));
            }
            Log.d("test--",sb.toString().substring(0,sb.toString().length() -1));
        }
    }

}
