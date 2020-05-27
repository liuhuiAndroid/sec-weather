package com.seckill.weather.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.WeatherAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.viewmodel.CustomViewModelProvider;
import com.seckill.weather.viewmodel.WeatherDetailViewModel;

public class WeatherDetailActivity extends BaseActivity {

    private WeatherDetailViewModel mWeatherDetailViewModel;
    private WeatherAdapter mWeatherAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        long cityId = getIntent().getLongExtra("CityId", -1);
        mWeatherDetailViewModel = new ViewModelProvider(this,
                CustomViewModelProvider.providerWeatherDetailViewModel(cityId))
                .get(WeatherDetailViewModel.class);
        String cityZh = getIntent().getStringExtra("CityZh");
        ((TextView) findViewById(R.id.mTvTitle)).setText(cityZh);
        findViewById(R.id.mIvBack).setVisibility(View.VISIBLE);
        findViewById(R.id.mIvBack).setOnClickListener(v -> finish());

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeatherAdapter = new WeatherAdapter();
        mRecyclerView.setAdapter(mWeatherAdapter);

        mWeatherDetailViewModel.getWeatherLiveData().observe(this,
                weather -> mWeatherAdapter.setWeather(weather));
        mWeatherDetailViewModel.getPm25LiveData().observe(this,
                pm25 -> mWeatherAdapter.setPm25(pm25));
    }

}
