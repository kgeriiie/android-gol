package com.ottone.gameoflife.ui.main;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.ottone.gameoflife.bl.EventManager;
import com.ottone.gameoflife.bl.PrefManager;
import com.ottone.gameoflife.bl.Board;

import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class MainPresenter extends MvpBasePresenter<IMainView> {

    private Board currentBoard;

    private Timer mUpdateTimer;
    private UpdaterTask updaterTask;

    public MainPresenter() {
        currentBoard = new Board(PrefManager.getInstance().getFieldWidth(), PrefManager.getInstance().getFieldHeight());

        currentBoard.addLife(0,1);
        currentBoard.addLife(0,2);
        currentBoard.addLife(0,3);
        currentBoard.addLife(1,2);
        currentBoard.addLife(4,3);
        currentBoard.addLife(3,2);
    }

    @Override
    public void attachView(IMainView view) {
        super.attachView(view);

        EventManager.getInstance().subscribeToEventBus(this);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);

        EventManager.getInstance().unsubscribeFromEventBus(this);
    }

    public void loadInitialBoard() {
        if (getView() != null)
            getView().showBoard(currentBoard);
    }

    public void loadEmptyInitialBoard(int columns, int rows) {
        if (getView() != null) {
            currentBoard = new Board(columns, rows);
            getView().showBoard(currentBoard);
        }
    }

    public void startAutoPlay() {
        getView().onAutomatStateChanged(true);
        getView().onManualStepEnableChanged(false);

        if (mUpdateTimer != null) {
            mUpdateTimer.cancel();
        }

        mUpdateTimer = new Timer();
        updaterTask = new UpdaterTask();

        mUpdateTimer.schedule(updaterTask,0,500);

    }

    public void stopAutoPlay() {
        getView().onAutomatStateChanged(false);
        getView().onManualStepEnableChanged(true);

        if (mUpdateTimer != null) {
            mUpdateTimer.cancel();
            mUpdateTimer = null;
        }
    }


    private class UpdaterTask extends TimerTask {

        @Override
        public void run() {
            getView().onEvolutionNeeded();
        }
    }

    @Subscribe
    public void onEvent(UpdateUiEvent event) {
        if (event.isOnlyCheckboxChanged) {
            getView().onBoardEditableChanged(PrefManager.getInstance().isEditable());
        } else {
            loadEmptyInitialBoard(PrefManager.getInstance().getFieldWidth(),PrefManager.getInstance().getFieldHeight());
        }
    }

    public static class UpdateUiEvent {
        boolean isOnlyCheckboxChanged = false;

        public UpdateUiEvent(boolean pIsOnlyCheckboxChanged) {
            isOnlyCheckboxChanged = pIsOnlyCheckboxChanged;
        }
    }
}
