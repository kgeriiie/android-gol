package com.ottone.gameoflife.ui.main;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.ottone.gameoflife.App;
import com.ottone.gameoflife.R;
import com.ottone.gameoflife.bl.Board;
import com.ottone.gameoflife.ui.custom.BoardView;
import com.ottone.gameoflife.ui.settings.SettingsDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.main_content_board)
    BoardView boardView;
    @BindView(R.id.main_auto_play_pause)
    ImageView playPauseBt;
    @BindView(R.id.main_next_stepBt)
    ImageView stepNextBt;

    SettingsDialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        resizeBoardView();

        settingsDialog = new SettingsDialog(this);

        getPresenter().loadInitialBoard();
    }

    private void resizeBoardView() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) boardView.getLayoutParams();
        params.height = App.getInstance().getWidth();
        params.width = App.getInstance().getWidth();

        boardView.requestLayout();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @OnClick(R.id.main_next_stepBt)
    void nextStep(View view) {
        boardView.doEvolution();
    }

    @OnClick(R.id.main_auto_play_pause)
    void automataPlay(View view) {
        if (stepNextBt.isEnabled()) {
            getPresenter().startAutoPlay();
        } else {
            getPresenter().stopAutoPlay();
        }
    }

    @Override
    public void showBoard(Board board) {
        boardView.setBoard(board);
    }

    @Override
    public void onManualStepEnableChanged(boolean isEnable) {
        stepNextBt.setEnabled(isEnable);
    }

    @Override
    public void onAutomatStateChanged(boolean isPlaying) {
        playPauseBt.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),isPlaying ? R.drawable.ic_pause_white_24dp : R.drawable.ic_play_arrow_white_24dp));

        Toast.makeText(getApplicationContext(),isPlaying ? R.string.automatic_simulation_on_text : R.string.automatic_simulation_off_text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEvolutionNeeded() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                boardView.doEvolution();
            }
        });
    }

    @Override
    public void onBoardEditableChanged(boolean isEditable) {
        boardView.isEditableEnabled(isEditable);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                settingsDialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
