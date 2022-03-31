package com.unionpay.app;

import android.app.Application;
import android.os.Bundle;

import com.unionpay.unionpaysdk.UnionpaySDKManager;

import timber.log.Timber;

public class WeatherApp extends Application {

    public static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 初始化日志
        Timber.plant(new Timber.DebugTree());

        Bundle bundle = new Bundle();
        bundle.putString(UnionpaySDKManager.KEY_APP_ISSUE, "49990063");
        bundle.putString(UnionpaySDKManager.KEY_RUNNING_MODE, "01");
        UnionpaySDKManager.getInstance(this).init(this, bundle);
    }
}
