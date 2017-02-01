package com.ottone.gameoflife;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class App extends Application {

    private static App sInstance;

    private static Context sContext;

    private int mHeight;
    private int mWidth;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        sContext = getApplicationContext();

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        mHeight = displayMetrics.heightPixels;
        mWidth = displayMetrics.widthPixels;
    }

    public static App getInstance() {
        return sInstance;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }
}
