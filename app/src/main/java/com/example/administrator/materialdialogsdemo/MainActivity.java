package com.example.administrator.materialdialogsdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

public class MainActivity extends AppCompatActivity {

    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.isAgree = false;

        materialDialog = new MaterialDialog.Builder(this)
                .canceledOnTouchOutside(false)
                .title(R.string.dialog_title)
                .content(R.string.dialog_content)
                .positiveColorRes(R.color.dialog_positive)
                .negativeColorRes(R.color.dialog_negative)
                .titleGravity(GravityEnum.START)
                .titleColorRes(R.color.dialog_title)
                .contentColorRes(R.color.dialog_content)
                .backgroundColorRes(R.color.dialog_background)
                .dividerColorRes(R.color.colorAccent)
                .positiveColor(0x60282726)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        materialDialog = null;
                        finish();
                    }
                })
                .negativeColor(0x60282726)
                .theme(Theme.DARK)
                .positiveText("")
                .negativeText("取消")
                .checkBoxPrompt("我已經閱讀並同意條款與條件", false, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            App.isAgree = true;
                            materialDialog.dismiss();
                            materialDialog = null;
                            initializationDialogModule(true);
                        }
                    }
                })
                .keyListener(keylistener)
                .show();
    }

    private void initializationDialogModule(boolean isAgree) {

        if (isAgree == true) {
            materialDialog = new MaterialDialog.Builder(this)
                    .canceledOnTouchOutside(false)
                    .title(R.string.dialog_title)
                    .content(R.string.dialog_content)
                    .positiveColorRes(R.color.dialog_positive)
                    .negativeColorRes(R.color.dialog_negative)
                    .titleGravity(GravityEnum.START)
                    .titleColorRes(R.color.dialog_title)
                    .contentColorRes(R.color.dialog_content)
                    .backgroundColorRes(R.color.dialog_background)
                    .dividerColorRes(R.color.colorAccent)
                    .btnSelector(R.drawable.md_btn_selector_custom, DialogAction.POSITIVE)
                    .positiveColor(0xff282726)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                            materialDialog.dismiss();
                            materialDialog = null;
                        }
                    })
                    .negativeColor(0x60282726)
                    .theme(Theme.DARK)
                    .positiveText("接受")
                    .negativeText("")
                    .checkBoxPrompt("我已經閱讀並同意條款與條件", true, new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b == false) {
                                App.isAgree = false;
                                materialDialog.dismiss();
                                materialDialog = null;
                                initializationDialogModule(false);
                            }
                        }
                    })
                    .keyListener(keylistener)
                    .show();
        } else {
            materialDialog = new MaterialDialog.Builder(this)
                    .canceledOnTouchOutside(false)
                    .title(R.string.dialog_title)
                    .content(R.string.dialog_content)
                    .positiveColorRes(R.color.dialog_positive)
                    .negativeColorRes(R.color.dialog_negative)
                    .titleGravity(GravityEnum.START)
                    .titleColorRes(R.color.dialog_title)
                    .contentColorRes(R.color.dialog_content)
                    .backgroundColorRes(R.color.dialog_background)
                    .dividerColorRes(R.color.colorAccent)
                    .positiveColor(0x60282726)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            materialDialog = null;
                            finish();
                        }
                    })
                    .negativeColor(0x60282726)
                    .theme(Theme.DARK)
                    .positiveText("")
                    .negativeText("取消")
                    .checkBoxPrompt("我已經閱讀並同意條款與條件", false, new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b == true) {
                                App.isAgree = true;
                                materialDialog.dismiss();
                                materialDialog = null;
                                initializationDialogModule(true);
                            }
                        }
                    })
                    .keyListener(keylistener)
                    .show();
        }
    }

    /**
     * 在Dialog里面 监听系统的按键, 讓返回鍵失效
     */
    private DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                if (App.isAgree == true) {
                    Toast.makeText(MainActivity.this, "請先點擊 接受", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "請先點擊 取消", Toast.LENGTH_SHORT).show();
                }
                return true;
            } else {
                return false;
            }
        }
    };
}
