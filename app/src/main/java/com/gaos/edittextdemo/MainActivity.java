package com.gaos.edittextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CursorRightOfHintEditText cursorRightOfHintEditText = (CursorRightOfHintEditText) findViewById(R.id.cursor_right_of_hint_edit_text);
        cursorRightOfHintEditText.setEditTextTextChangedListener(new CursorRightOfHintEditText.EditTextTextChangedListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: s = " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
