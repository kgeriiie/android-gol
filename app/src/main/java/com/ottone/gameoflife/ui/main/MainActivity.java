package com.ottone.gameoflife.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.ottone.gameoflife.R;
import com.ottone.gameoflife.bo.Board;
import com.ottone.gameoflife.ui.custom.BoardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_content_board)
    FrameLayout root;

    BoardView boardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Board b = new Board(33,33);
        b.addLife(0,1);
        b.addLife(0,2);
        b.addLife(0,3);
        b.addLife(1,2);
        b.addLife(4,3);
        b.addLife(3,2);

        boardView = new BoardView(this);
        boardView.setBoard(b);

        root.addView(boardView);

    }

    @OnClick(R.id.main_next_stepBt)
    void nextStep(View view) {
        boardView.doEvolution();
    }
}
