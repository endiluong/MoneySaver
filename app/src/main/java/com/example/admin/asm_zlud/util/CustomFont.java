package com.example.admin.asm_zlud.util;

import android.app.Application;

public class CustomFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontOverride.setDefaultFont(this, "DEFAULT", "fonts/Lato-Regular.ttf");
        FontOverride.setDefaultFont(this, "MONOSPACE", "fonts/Lato-Regular.ttf");
        FontOverride.setDefaultFont(this, "SERIF", "fonts/Lato-Regular.ttf");
        FontOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Lato-Regular.ttf");
    }
}
