package com.example.huynguyen.ggs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText us,pas;
    Button bL,bR,bF,bG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatAccount();
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
                Toast.makeText(MainActivity.this,"Dang Update",Toast.LENGTH_LONG).show();
            }
        });
        bG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Dang Update",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Login() {
        Intent intent = new Intent(MainActivity.this, Voice.class);
        startActivity(intent);
    }

    private void CreatAccount() {
        LayoutInflater inflater = getLayoutInflater();
        View register = inflater.inflate(R.layout.register,null);
        final EditText user = register.findViewById(R.id.edEmail);
        final EditText pass = register.findViewById(R.id.edPass);
        final EditText conpass = register.findViewById(R.id.edCP);
        final TextView textView = register.findViewById(R.id.view1);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(register);
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Register");
                String us = user.getText().toString();
                String p = pass.getText().toString();
                String pp = conpass.getText().toString();
                Subitem subitem = new Subitem(us, p,pp);
                if(p.equals(pp)) {
                    myRef.child("GGS_register").push().setValue(subitem, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "khong the dang ky dc", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    textView.setText("sai mk");
                }
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void AnhXa() {
        us = findViewById(R.id.user);
        pas= findViewById(R.id.pass);
        bL= findViewById(R.id.login);
        bR= findViewById(R.id.register);
        bF= findViewById(R.id.FP);
        bG= findViewById(R.id.GG);
    }


}
