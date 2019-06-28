package com.example.coolweather;

import android.app.Application;

import org.litepal.LitePal;

public class CoolWeatherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
