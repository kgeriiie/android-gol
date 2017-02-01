package com.ottone.gameoflife.bl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ottone.gameoflife.App;

/**
 * Created by Geri on 2014.06.19..
 */
public class PrefManager {

    private static PrefManager mInstance = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String IS_EDITABLE = "IS_EDITABLE";

    private static final String FIELD_WIDTH = "FIELD_WIDTH";
    private static final String FIELD_HEIGHT = "FIELD_HEIGHT";

    private PrefManager() {
        this.context = App.getInstance().getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public static PrefManager getInstance() {
        if(mInstance == null) {
            mInstance = new PrefManager();
        }
        return mInstance;
    }

    public void setIsEditable(boolean isEditable) {
        editor.putBoolean(IS_EDITABLE, isEditable);
        editor.commit();
    }

    public boolean isEditable() {
        return sharedPreferences.getBoolean(IS_EDITABLE, false);
    }

    public void setFieldWidth(int size) {
        editor.putInt(FIELD_WIDTH, size);
        editor.commit();
    }

    public void setFieldHeight(int size) {
        editor.putInt(FIELD_HEIGHT, size);
        editor.commit();
    }

    public int getFieldWidth() {
        return sharedPreferences.getInt(FIELD_WIDTH, 10);
    }

    public int getFieldHeight() {
        return sharedPreferences.getInt(FIELD_HEIGHT, 10);
    }
}
