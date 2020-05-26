package com.seckill.weather.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.seckill.weather.Constants;
import com.seckill.weather.R;
import com.seckill.weather.adapter.WeatherAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.data.Weather;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

import timber.log.Timber;

public class WeatherDetailActivity extends BaseActivity {

    private WeatherAdapter mWeatherAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        String cityZh = getIntent().getStringExtra("CityZh");
        ((TextView) findViewById(R.id.mTvTitle)).setText(cityZh + "天气");

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeatherAdapter = new WeatherAdapter();
        loadData();
    }

    private void loadData() {
        long cityId = getIntent().getLongExtra("CityId", -1);
        OkGo.<String>get(Constants.BASE_URL)
                .params("appid", Constants.APP_ID)
                .params("appsecret", Constants.APP_SECRET)
                .params("version", "v9")
                .params("cityid", cityId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Moshi moshi = new Moshi.Builder().build();
                            JsonAdapter<Weather> jsonAdapter = moshi.adapter(Weather.class);
                            Weather weather = jsonAdapter.fromJson(response.body());
                            mWeatherAdapter.setWeather(weather);
                            mRecyclerView.setAdapter(mWeatherAdapter);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
