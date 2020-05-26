package com.seckill.weather;

import android.app.Application;

import timber.log.Timber;

public class WeatherApplication extends Application {

    public static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 初始化日志
        Timber.plant(new Timber.DebugTree());
    }
}
