package com.example.liteutils;

import android.app.Application;

import net.smartbetter.android.liteutils.common.LogUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.setDebug(true); //Open Log Debug.
    }
}
