package com.example.huynguyen.ggs;


import android.content.Intent;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button bLogin, bRegister, bFP, bGoogle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();
            }
        });
        bFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Dang Update", Toast.LENGTH_LONG).show();
            }
        });
        bGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Dang Update", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void Login() {
        mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           /* Log.d(Tag, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);*/
                        } else {
                            // If sign in fails, display a message to the user.
                         /*   Log.w(Tag, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);*/
                        }

                        // ...
                    }
                });
        Intent intent = new Intent(MainActivity.this, Voice.class);
        startActivity(intent);


    }

    private void Dangnhap() {

    }

    private boolean checkdta() {
        if (TextUtils.isEmpty(username.getText().toString()) && TextUtils.isEmpty(password.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(username.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            return false;
        }
        return true;
    }

    private void AnhXa() {
        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        bLogin = findViewById(R.id.btnLogin);
        bRegister = findViewById(R.id.btnRegister);
        bFP = findViewById(R.id.FP);
        bGoogle = findViewById(R.id.loginGG);
    }
}

