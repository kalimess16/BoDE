package com.example.huynguyen.ggs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

    EditText username,password;
    Button bLogin,bRegister,bFP,bGoogle;
    FirebaseDatabase database;
    DatabaseReference myRef;
    LayoutInflater inflater;
    View register;
    EditText first,last,phone,email,user,pass,confirm;
    TextView view;
    String a,b,d,e,f,g;
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatAccount();
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
                Toast.makeText(MainActivity.this,"Dang Update",Toast.LENGTH_LONG).show();
            }
        });
        bGoogle.setOnClickListener(new View.OnClickListener() {
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
        inflater = getLayoutInflater();
        register = inflater.inflate(R.layout.register,null);
        AnhxaRegister();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(register);
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Register");
                 a = first.getText().toString();
                 b = last.getText().toString();
                 c = Integer.parseInt(phone.getText().toString());
                 d = email.getText().toString();
                 e = user.getText().toString();
                 f = pass.getText().toString();
                 g = confirm.getText().toString();
                 if(CheckRegister() == true){
                     uplenfirebase();
                 }
                 else{
                     Toast.makeText(MainActivity.this, "khong the dang ky dc", Toast.LENGTH_LONG).show();
                 }

            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
       /* AlertDialog dialog = builder.create();
        dialog.show();*/

    }

    private void uplenfirebase() {
        Subitem subitem = new Subitem(a,b,c,d,e,f);
        if(f.equals(g)) {
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
            view.setText("sai mk");
            Toast.makeText(MainActivity.this, "ko lam dc", Toast.LENGTH_LONG).show();
        }
    }

    private void AnhxaRegister() {
          first = register.findViewById(R.id.edFirstname);
          last = register.findViewById(R.id.edLastName);
          phone = register.findViewById(R.id.edPhone);
          email = register.findViewById(R.id.edEmail);
          user = register.findViewById(R.id.edUsername);
          pass = register.findViewById(R.id.edPass);
          confirm = register.findViewById(R.id.edCP);
          view = register.findViewById(R.id.view);
    }

    private boolean CheckRegister() {
        if(TextUtils.isEmpty(a) && TextUtils.isEmpty(b)  && TextUtils.isEmpty(d)&& TextUtils.isEmpty(e) && TextUtils.isEmpty(f)&& TextUtils.isEmpty(g)){
           return false;
        }

       /* if(TextUtils.isEmpty(a)){
            Toast.makeText(MainActivity.this,"Please enter first name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(b)){
            Toast.makeText(MainActivity.this,"Please enter last name",Toast.LENGTH_LONG).show();
        }*/
        if(TextUtils.isEmpty(c+"")){
            return false;
        }
        else{
            return true;
        }
       /* if(TextUtils.isEmpty(d)){
            Toast.makeText(MainActivity.this,"Please enter email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(e)){
            Toast.makeText(MainActivity.this,"Please enter user name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(f)){
            Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(g)){
            Toast.makeText(MainActivity.this,"Please enter confirm password",Toast.LENGTH_LONG).show();
        }*/
    }


    private void AnhXa() {
        username = findViewById(R.id.user);
        password= findViewById(R.id.pass);
        bLogin= findViewById(R.id.login);
        bRegister= findViewById(R.id.register);
        bFP= findViewById(R.id.FP);
        bGoogle= findViewById(R.id.GG);
    }

}
