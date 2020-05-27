package com.seckill.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.CityAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.data.CityType;
import com.seckill.weather.inter.OnItemClickListener;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

public class CityListActivity extends BaseActivity {

    private CityAdapter mCityAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String provinceZh = getIntent().getStringExtra("provinceZh");
        ((TextView) findViewById(R.id.mTvTitle)).setText(provinceZh);
        findViewById(R.id.mIvBack).setVisibility(View.VISIBLE);
        findViewById(R.id.mIvBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String cityResult = getIntent().getStringExtra("City");
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List<City>> jsonAdapter =
                moshi.adapter(Types.newParameterizedType(List.class, City.class));
        try {
            final List<City> cityList = jsonAdapter.fromJson(cityResult);

            RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mCityAdapter = new CityAdapter(CityType.CITY);

            mCityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(CityListActivity.this, WeatherDetailActivity.class);
                    City city = cityList.get(position);
                    intent.putExtra("CityId", city.getId());
                    intent.putExtra("CityZh", city.getCityZh());
                    startActivity(intent);
                }
            });

            mRecyclerView.setAdapter(mCityAdapter);
            // 添加分割线
            DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(decoration);

            mCityAdapter.setCityList(cityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
