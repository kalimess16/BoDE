package com.example.huynguyen.ggs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText us,pas;
    Button bL,bR,bF;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        bL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        bF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Dang Update", Toast.LENGTH_LONG).show();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

    }

    private void Login() {
     /*   Intent intent = new Intent(MainActivity.this, Voice.class);
        startActivity(intent);*/
        if (verifyData()) {
            dangNhap();
        }

    }

    private void dangNhap() {

    }

    private boolean verifyData() {
        if (TextUtils.isEmpty(us.getText().toString()) && TextUtils.isEmpty(pas.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(us.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(pas.getText().toString())) {
            return false;
        }
        return true;
    }

    private void AnhXa() {
        us = findViewById(R.id.user);
        pas= findViewById(R.id.pass);
        bL= findViewById(R.id.sign_in_button);
        bR= findViewById(R.id.btnRegister);
        bF= findViewById(R.id.FP);
 //       bG= findViewById(R.id.sign_in_button);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
           /* Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);*/
            Intent intent = new Intent(MainActivity.this, Voice.class);
            startActivity(intent);
        }

    }
}

