package com.ivoryworks.romajiconvertersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ivoryworks.romajiconverter.KanaToRomaji;
import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;
import com.ivoryworks.romajiconverter.RomajiToKana;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RomajiSystem mSystem = RomajiSystem.HEPBURN;
    private int mDirection = 0;

    private TextView mTextViewResult;
    private CharSequence mCharSequence;
    private int mStart;
    private int mBefore;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = ((TextView) findViewById(R.id.text_result));
        ((EditText) findViewById(R.id.edit_from)).addTextChangedListener(mWatchHandler);
        setupRadioGroupSystem();
        setupRadioGroupDirection();
    }

    private TextWatcher mWatchHandler = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            mCharSequence = charSequence;
            mStart = start;
            mBefore = before;
            mCount = count;
            List<String> convertStrings;
            if (mDirection == 0) {
                convertStrings = KanaToRomaji.convert(charSequence.toString(), mSystem);
            } else {
                convertStrings = RomajiToKana.convert(charSequence.toString(), mSystem);
            }
            String strBuf = "";
            for (String str : convertStrings) {
                strBuf += str + "\n";
            }
            mTextViewResult.setText(strBuf);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void setupRadioGroupSystem() {
        RadioGroup radioSystem = (RadioGroup) findViewById(R.id.radio_system);
        radioSystem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_hepburn:
                        mSystem = RomajiSystem.HEPBURN;
                        break;
                    case R.id.radio_kunrei:
                        mSystem = RomajiSystem.KUNREI;
                        break;
                    case R.id.radio_nihon:
                        mSystem = RomajiSystem.NIHON;
                        break;
                }
                if (mCharSequence != null) {
                    mWatchHandler.onTextChanged(mCharSequence, mStart, mBefore, mCount);
                }
            }
        });
    }

    private void setupRadioGroupDirection() {
        RadioGroup radioDirection = (RadioGroup) findViewById(R.id.radio_direction);
        radioDirection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_kana_to_romaji:
                        mDirection = 0;
                        break;
                    case R.id.radio_romaji_to_kana:
                        mDirection = 1;
                        break;
                }
                if (mCharSequence != null) {
                    mWatchHandler.onTextChanged(mCharSequence, mStart, mBefore, mCount);
                }
            }
        });
    }
}
