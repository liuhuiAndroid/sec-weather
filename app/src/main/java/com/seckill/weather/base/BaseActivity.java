package com.seckill.weather.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.seckill.weather.utilities.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式
        StatusBarUtil.fitSystemBar(this);
    }
}
