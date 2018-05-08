package com.example.huynguyen.ggs;

/**
 * Created by HuyNguyen on 4/17/2018.
 */

public class Subitem {
    String firstname,lastname,Email,username,password;
    int phone;
    public Subitem(){

    }
    public Subitem(String first, String last, int number, String email, String user, String pass){
       this.firstname = first;
       this.lastname = last;
       this.phone = number;
       this.Email = email;
       this.username = user;
       this.password = pass;
    }
}
