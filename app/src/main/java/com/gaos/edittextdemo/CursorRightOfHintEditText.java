package com.gaos.edittextdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Author:　Created by benjamin
 * DATE :  2017/11/1 15:08
 * versionCode:　v2.2
 */

public class CursorRightOfHintEditText extends RelativeLayout {
    private static final String TAG = "CursorRightOfHintEditTe";
    private static final int BLINK = 500;
    private ImageView tvETCursor;
    private static final int MSG_CURSOR_BLINK = 0x001;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (tvETCursor != null) {

                if (tvETCursor.getVisibility() == View.VISIBLE) {

                    tvETCursor.setVisibility(View.INVISIBLE);
                } else if (tvETCursor.getVisibility() == View.INVISIBLE) {

                    tvETCursor.setVisibility(View.VISIBLE);
                }
                mHandler.sendEmptyMessageDelayed(MSG_CURSOR_BLINK, BLINK);
            }
            return true;
        }
    });
    private LinearLayout llETHint;
    private EditText editText;

    public CursorRightOfHintEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_curosr_right_of_hint_edittext, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        editText = (EditText) findViewById(R.id.et_demo);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (editTextTextChangedListener != null) {
                    editTextTextChangedListener.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: s = " + s);
                if (TextUtils.isEmpty(s)) {
                    mHandler.sendEmptyMessageDelayed(MSG_CURSOR_BLINK, BLINK);
                    llETHint.setVisibility(View.VISIBLE);
                    editText.setCursorVisible(false);
                } else {
                    llETHint.setVisibility(View.INVISIBLE);
                    mHandler.removeCallbacksAndMessages(null);
                    editText.setCursorVisible(true);
                }
                if (editTextTextChangedListener != null) {
                    editTextTextChangedListener.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextTextChangedListener != null) {
                    editTextTextChangedListener.afterTextChanged(s);
                }
            }
        });

        tvETCursor = (ImageView) findViewById(R.id.iv_et_cursor);

        mHandler.sendEmptyMessageDelayed(MSG_CURSOR_BLINK, BLINK);

        editText.setCursorVisible(false);

        llETHint = (LinearLayout) findViewById(R.id.ll_et_hint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface EditTextTextChangedListener {

        void beforeTextChanged(CharSequence s, int start,
                               int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);


        void afterTextChanged(Editable s);
    }

    private EditTextTextChangedListener editTextTextChangedListener;

    public void setEditTextTextChangedListener(EditTextTextChangedListener editTextTextChangedListener) {
        this.editTextTextChangedListener = editTextTextChangedListener;
    }
}
