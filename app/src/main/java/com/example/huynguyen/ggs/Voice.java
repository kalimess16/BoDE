package com.example.huynguyen.ggs;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by HuyNguyen on 4/14/2018.
 */

public class Voice extends Activity {
    public TextView txtSubject;
    public TextView txtResultFromVoice;
    public ImageButton btnVoice, btnPrevious, btnNext;

    private static final String TAG = "VOICE ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efun);
        AnhXa();
        setEventForButton();
    }

    private void setEventForButton() {
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputVoice();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Voice.this, "Dang Update", Toast.LENGTH_LONG).show();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Voice.this, "Dang Update", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getInputVoice() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_LONG).show();
        }
    }

    private void AnhXa() {
        txtSubject = findViewById(R.id.txtSubject);
        txtResultFromVoice = findViewById(R.id.txtResultFromVoice);
        btnVoice = findViewById(R.id.btnVoice);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Log.d(TAG, result.get(0));
                txtResultFromVoice.setText(result.get(0));
            }
        }
    }
}
