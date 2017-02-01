package com.ottone.gameoflife.ui.main;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.ottone.gameoflife.bl.Board;

/**
 * Created by koleszargergo on 2/1/17.
 */

public interface IMainView extends MvpView {
    void showBoard(Board board);
    void onManualStepEnableChanged(boolean isEnable);
    void onAutomatStateChanged(boolean isPlaying);
    void onEvolutionNeeded();
    void onBoardEditableChanged(boolean isEditable);
}
