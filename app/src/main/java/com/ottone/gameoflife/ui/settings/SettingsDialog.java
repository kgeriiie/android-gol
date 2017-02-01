package com.ottone.gameoflife.ui.settings;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ottone.gameoflife.R;
import com.ottone.gameoflife.bl.EventManager;
import com.ottone.gameoflife.bl.PrefManager;
import com.ottone.gameoflife.ui.main.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by koleszargergo on 2/1/17.
 */

public class SettingsDialog {

    @BindView(R.id.settings_columns_countEt)
    EditText mColumnsEt;
    @BindView(R.id.settings_rows_countEt)
    EditText mRowsEt;

    @BindView(R.id.settings_is_editable_enabledCb)
    CheckBox mIsEditableEnabledCb;

    private Dialog mDialog;

    private Context mContext;

    public SettingsDialog(Context pContext) {
        this.mContext = pContext;

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_settings);

        ButterKnife.bind(this, mDialog);

        mColumnsEt.setText(String.format("%d", PrefManager.getInstance().getFieldWidth()));
        mRowsEt.setText(String.format("%d", PrefManager.getInstance().getFieldHeight()));
        mIsEditableEnabledCb.setChecked(PrefManager.getInstance().isEditable());
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        } else {
            mDialog.hide();
        }
    }

    public void hide() {
        if (mDialog.isShowing()) {
            mDialog.hide();
        }
    }

    @OnClick(R.id.settings_saveBt)
    void onSaveClicked(View v) {
        int mColumns = Integer.parseInt(mColumnsEt.getText().toString());
        int mRows = Integer.parseInt(mRowsEt.getText().toString());

        if (mColumns < 1) {
            Toast.makeText(mContext,R.string.settings_width_size_warning_text,Toast.LENGTH_SHORT).show();

            return;
        }

        if (mRows < 1) {
            Toast.makeText(mContext,R.string.settings_height_size_warning_text,Toast.LENGTH_SHORT).show();

            return;
        }

        if (mColumns != PrefManager.getInstance().getFieldWidth() || mRows != PrefManager.getInstance().getFieldHeight() || mIsEditableEnabledCb.isChecked() != PrefManager.getInstance().isEditable()) {

            boolean isOnlyCheckboxChanged = (mColumns == PrefManager.getInstance().getFieldWidth() && mRows == PrefManager.getInstance().getFieldHeight());

            PrefManager.getInstance().setFieldWidth(mColumns);
            PrefManager.getInstance().setFieldHeight(mRows);

            PrefManager.getInstance().setIsEditable(mIsEditableEnabledCb.isChecked());

            EventManager.getInstance().sendEvent(new MainPresenter.UpdateUiEvent(isOnlyCheckboxChanged));
        }

        hide();
    }
}
