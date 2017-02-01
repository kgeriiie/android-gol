package com.ottone.gameoflife.bo;

import com.ottone.gameoflife.bl.Board;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by koleszargergo on 2/1/17.
 */
public class BoardTest {

    private static final int[][] start = new int[][]{
            {0,0,0,0,0},
            {1,0,0,0,0},
            {1,1,0,1,0},
            {1,0,0,0,1},
            {0,0,0,0,0}
    };

    private static final int[][] firstStepRules = new int[][]{
            {0,0,0,0,0},
            {1,1,0,0,0},
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,0,0,0}
    };



    Board board;

    @Before
    public void initialize() {
        board = new Board(5,5);
        board.addLife(0,1);
        board.addLife(0,2);
        board.addLife(0,3);
        board.addLife(1,2);
        board.addLife(4,3);
        board.addLife(3,2);

        for (int i = 0; i < start.length; i++) {
            Assert.assertEquals(Arrays.toString(start[i]),Arrays.toString(board.getBoard()[i]));
        }
    }

    @Test
    public void applyRules() throws Exception {
        board.applyRules();

        for (int i = 0; i < start.length; i++) {
            Assert.assertEquals(Arrays.toString(firstStepRules[i]),Arrays.toString(board.getBoard()[i]));
        }
    }

    @Test
    public void testRules() throws Exception  {
        int newState1 = board.getNewStateOfCellWithNotations(0,0);
        int newState2 = board.getNewStateOfCellWithNotations(0,1);
        int newState3 = board.getNewStateOfCellWithNotations(1,3);

        Assert.assertEquals(CellState.EMPTY,newState1);
        Assert.assertEquals(CellState.LIVE,newState2);
        Assert.assertEquals(CellState.BIRTH,newState3);
    }

}