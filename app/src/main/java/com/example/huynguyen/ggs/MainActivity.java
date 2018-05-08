package com.example.huynguyen.ggs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button bLogin, bRegister, bFP, bGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

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
     /*   Intent intent = new Intent(MainActivity.this, Voice.class);
        startActivity(intent);*/
        if (checkdta()) {
            Dangnhap();
        }

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

