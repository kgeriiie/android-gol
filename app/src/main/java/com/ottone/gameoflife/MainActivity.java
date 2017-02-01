package com.ottone.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ottone.gameoflife.bo.Board;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Board b = new Board(5,5);
        b.addLife(0,1);
        b.addLife(0,2);
        b.addLife(0,3);
        b.addLife(1,2);
        b.addLife(4,3);
        b.addLife(3,2);

        b.show();

        Log.d("test--", "---");

        b.applyRules();

        b.show();

        Log.d("test--", "---");

        b.applyRules();

        b.show();


        Log.d("test--", "---");

        b.applyRules();

        b.show();

    }
}
