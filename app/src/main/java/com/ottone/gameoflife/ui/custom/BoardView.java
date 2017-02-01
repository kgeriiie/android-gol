package com.ottone.gameoflife.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ottone.gameoflife.bo.Board;
import com.ottone.gameoflife.bo.CellState;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class BoardView extends View {
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();

    private Board mBoard;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setBoard(Board pBoard) {
        mBoard = pBoard;

        calculateDimensions();
    }

    public void doEvolution() {
        mBoard.applyRules();

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (mBoard.getColumnsCount() < 1 || mBoard.getRowsCount() < 1) {
            return;
        }

        cellWidth = getWidth() / mBoard.getColumnsCount();
        cellHeight = getHeight() / mBoard.getRowsCount();

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (mBoard.getColumnsCount() == 0 || mBoard.getRowsCount() == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < mBoard.getColumnsCount(); i++) {
            for (int j = 0; j < mBoard.getRowsCount(); j++) {
                if (mBoard.getCellStateOfBoard(i,j) == CellState.LIVE) {

                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, blackPaint);
                }
            }
        }

        for (int i = 1; i < mBoard.getColumnsCount(); i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < mBoard.getRowsCount(); i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            if (column <= mBoard.getColumnsCount() && row <= mBoard.getRowsCount()) {
                mBoard.setCellStateOfBoard(column, row, mBoard.getCellStateOfBoard(column, row) == CellState.LIVE ? CellState.EMPTY : CellState.LIVE);
                invalidate();
            }
        }

        return true;
    }
}