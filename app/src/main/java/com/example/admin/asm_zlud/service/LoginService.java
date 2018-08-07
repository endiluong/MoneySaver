package com.example.admin.asm_zlud.service;

import android.util.Log;

public class LoginService {

    public boolean login(String id, String password) {
        if (id.equals("admin")&&(password.equals("admin"))) {
            Log.i("a","True");
            return true;
        } else {
            Log.i("a","False");
            return false;
        }
    }
}
