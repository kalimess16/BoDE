package com.example.huynguyen.ggs;

/**
 * Created by HuyNguyen on 4/17/2018.
 */

public class Subitem {
    String user;
    String conP;
    String pass;

    public Subitem(){

    }
    public Subitem(String name,String mk,String xnmk){
        this.user = name;
        this.pass = mk;
        this.conP = xnmk;
    }
}
