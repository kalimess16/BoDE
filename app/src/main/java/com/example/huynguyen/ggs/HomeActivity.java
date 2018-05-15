package com.example.huynguyen.ggs;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynguyen.ggs.model.Chatbot;
import com.example.huynguyen.ggs.model.SentenceCompare;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public TextView txtSubject;
    public TextView txtResultFromVoice;
    public ImageButton btnVoice, btnNext;

    private static final String TAG = "HOME_ACTIVITY";

    SentenceCompare sentenceCompare;

    String strSubject;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        addEvent();
    }

    private void addEvent() {
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputVoice();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }




    @SuppressLint("SetTextI18n")
    private void initView() {
        txtSubject = findViewById(R.id.txtSubject);
        txtResultFromVoice = findViewById(R.id.txtResultFromVoice);
        btnVoice = findViewById(R.id.btnVoice);
        btnNext = findViewById(R.id.btnNext);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Log.d(TAG, result.get(0));
                txtResultFromVoice.setText(result.get(0));
                String resultVoice = result.get(0);
                //String strSubject = txtSubject.getText().toString();
                sentenceCompare = new SentenceCompare(strSubject, resultVoice);
                txtSubject.setText(sentenceCompare.spanString());
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_topics) {
            Intent topicIntent = new Intent(HomeActivity.this, TopicActivity.class);
            startActivity(topicIntent);
        } else if (id == R.id.nav_process) {
            Intent topicIntent = new Intent(HomeActivity.this, ProcessingActivity.class);
            startActivity(topicIntent);
        } else if (id == R.id.nav_chat_bot) {
            Intent topicIntent = new Intent(HomeActivity.this, Chatbot.class);
            startActivity(topicIntent);
        } else if (id == R.id.nav_chat_stranger) {
            anountationEvent("Tui chưa có cập nhật");
        } else if (id == R.id.nav_setting) {
            anountationEvent("Tui chưa có cập nhật");
        } else if (id == R.id.nav_logout) {
            anountationEvent("Tui chưa có cập nhật");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void anountationEvent(String message){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}
