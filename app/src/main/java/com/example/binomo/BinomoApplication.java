package com.example.binomo;

import android.app.Application;

public class BinomoApplication extends Application {

    private static BinomoApplication mApplicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationInstance = this;
    }

    public static BinomoApplication getApplication() {
        return mApplicationInstance;
    }
}
