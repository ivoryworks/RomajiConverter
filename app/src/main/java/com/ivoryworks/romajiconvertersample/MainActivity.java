package com.ivoryworks.romajiconvertersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ivoryworks.romajiconverter.KanaToRomaji;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((EditText) findViewById(R.id.edit_from)).addTextChangedListener(watchHandler);
    }

    private TextWatcher watchHandler = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            KanaToRomaji k2r = new KanaToRomaji();
            List<String> convertStrings = k2r.convert(charSequence.toString());
            String strBuf = "";
            for (String str : convertStrings) {
                strBuf += str + "\n";
            }
            ((TextView) findViewById(R.id.text_result)).setText(strBuf);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
