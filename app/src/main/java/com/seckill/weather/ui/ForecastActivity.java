package com.seckill.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.seckill.weather.R;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.viewmodel.ForecastViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends BaseActivity {

    private ForecastViewModel mForecastViewModel;

    @BindView(R.id.mTvTitle)
    TextView mTvTitle;
    @BindView(R.id.mImageView)
    ImageView mImageView;
    @BindView(R.id.mTvChooseCity)
    AppCompatButton mTvChooseCity;
    @BindView(R.id.mTvCollect)
    AppCompatButton mTvCollect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        mForecastViewModel = new ViewModelProvider(this)
                .get(ForecastViewModel.class);
        mTvTitle.setText("全国天气降水量预报图");

        mTvChooseCity.setOnClickListener(v ->
                startActivity(new Intent(ForecastActivity.this, ProvinceListActivity.class)));
        mTvCollect.setOnClickListener(v ->
                startActivity(new Intent(ForecastActivity.this, CollectedCityActivity.class)));
        loadData();
    }

    private void loadData() {
        mForecastViewModel.getForecastLiveData().observe(this, forecast -> {
            Glide.with(ForecastActivity.this)
                    .load(forecast.getData().get(0).getPic())
                    .into(mImageView);
        });
    }
}
