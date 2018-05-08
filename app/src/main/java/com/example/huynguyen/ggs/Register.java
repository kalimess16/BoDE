package com.example.huynguyen.ggs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HuyNguyen on 4/26/2018.
 */

public class Register extends AppCompatActivity {


    EditText first,last,phone,email,user,pass,confirm;
    Button btCl,btRe;
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String fi,la,em,us,pa,co,dt;
    int ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Anhxa();
        Connectfirebase();
        btRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatAccount();
            }
        });
        btCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClosedRe();
                Toast.makeText(Register.this,"Closed",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ClosedRe() {
        Intent intent = new Intent(Register.this,MainActivity.class);
        startActivity(intent);
    }

    private void CreatAccount() {
        getdata();
        if(Checkdata() == true){
            UpFirebase();
        }
        else
        {
            Toast.makeText(Register.this,"không thể đăng ký được.",Toast.LENGTH_LONG).show();
        }
    }

    private void UpFirebase() {
        Subitem subitem = new Subitem(fi,la,ph,em,us,pa);
        if(pa.equals(co)) {
            myRef.child("GGS_register").push().setValue(subitem, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(Register.this, "Success", Toast.LENGTH_LONG).show();
                        ClosedRe();
                    } else {
                        Toast.makeText(Register.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{

            Toast.makeText(Register.this, "ko lam dc", Toast.LENGTH_LONG).show();
        }
    }

    private boolean Checkdata() {

        if (TextUtils.isEmpty(fi) || TextUtils.isEmpty(la) ||
                TextUtils.isEmpty(em) || TextUtils.isEmpty(us) ||
                TextUtils.isEmpty(pa) || TextUtils.isEmpty(co) ||
                TextUtils.isEmpty(dt) ) {
            return false;
        }

        if(pa.length()<6){
            textView.setText("Password is too short");
            return false;
        }
        if(dt.length()<9 && dt.length() > 12)
        {
            return false;
        }
        return true;
    }

    private void Connectfirebase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Register");
    }

    private void getdata(){
        fi = first.getText().toString();
        la = last.getText().toString();
        ph = Integer.parseInt(phone.getText().toString());
        dt = String.valueOf(ph);
        em = email.getText().toString();
        us = user.getText().toString();
        pa = pass.getText().toString();
        co = confirm.getText().toString();
    }

    private void Anhxa() {
        first = findViewById(R.id.edFirstname);
        last = findViewById(R.id.edLastName);
        phone = findViewById(R.id.edPhone);
        email = findViewById(R.id.edEmail);
        user = findViewById(R.id.edUsername);
        pass = findViewById(R.id.edPass);
        confirm = findViewById(R.id.edCP);
        btCl = findViewById(R.id.btCl);
        btRe = findViewById(R.id.btRegister);
        textView = findViewById(R.id.view);
    }
}
