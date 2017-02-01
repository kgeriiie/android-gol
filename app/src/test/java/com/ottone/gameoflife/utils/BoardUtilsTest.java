package com.ottone.gameoflife.utils;

import com.ottone.gameoflife.bo.Board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by koleszargergo on 2/1/17.
 */
public class BoardUtilsTest {
    @Test
    public void countNeighbourCells5x5() throws Exception {
        int[][] testBoard = new int[][]{
            {0,0,0,1,0},
            {0,0,0,0,1},
            {0,0,0,0,0},
            {0,1,1,0,0},
            {0,0,1,0,0}
        };

        int count1 = BoardUtils.countNeighbourCells(3,1,testBoard);
        int count2 = BoardUtils.countNeighbourCells(1,4,testBoard);
        int count3 = BoardUtils.countNeighbourCells(0,0,testBoard);
        int count4 = BoardUtils.countNeighbourCells(4,0,testBoard);

        assertEquals(count1,2);
        assertEquals(count2,3);
        assertEquals(count3,0);
        assertEquals(count4,2);
    }

    @Test
    public void countNeighbourCells3x3() throws Exception {
        int[][] testBoard = new int[][]{
                {0,1,0},
                {0,0,0},
                {0,0,1}
        };

        int count1 = BoardUtils.countNeighbourCells(0,2,testBoard);
        int count2 = BoardUtils.countNeighbourCells(1,1,testBoard);
        int count3 = BoardUtils.countNeighbourCells(0,0,testBoard);
        int count4 = BoardUtils.countNeighbourCells(2,1,testBoard);

        assertEquals(count1,0);
        assertEquals(count2,2);
        assertEquals(count3,1);
        assertEquals(count4,2);
    }

    @Test
    public void countNeighbourCells2x2() throws Exception {
        int[][] testBoard = new int[][]{
                {0,1},
                {0,0}
        };

        int count1 = BoardUtils.countNeighbourCells(0,1,testBoard);
        int count2 = BoardUtils.countNeighbourCells(1,1,testBoard);
        int count3 = BoardUtils.countNeighbourCells(0,0,testBoard);
        int count4 = BoardUtils.countNeighbourCells(1,0,testBoard);

        assertEquals(count1,1);
        assertEquals(count2,1);
        assertEquals(count3,1);
        assertEquals(count4,0);
    }

    @Test
    public void countNeighbourCellsWithBoard() throws Exception {

        Board b = new Board(5,5);
        b.addLife(0,1);
        b.addLife(0,2);
        b.addLife(0,3);
        b.addLife(1,2);
        b.addLife(4,3);
        b.addLife(3,2);

        int count1 = BoardUtils.countNeighbourCells(0,0,b.getBoard());
        int count2 = BoardUtils.countNeighbourCells(2,0,b.getBoard());
        int count3 = BoardUtils.countNeighbourCells(0,1,b.getBoard());
        int count4 = BoardUtils.countNeighbourCells(0,2,b.getBoard());

        assertEquals(count1,1);
        assertEquals(count2,0);
        assertEquals(count3,2);
        assertEquals(count4,3);
    }
}