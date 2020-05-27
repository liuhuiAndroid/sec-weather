package com.seckill.weather.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.CityAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.data.CityType;
import com.seckill.weather.inter.OnItemClickListener;
import com.seckill.weather.utilities.JsonUtils;
import com.seckill.weather.viewmodel.CityViewModel;
import com.seckill.weather.viewmodel.CustomViewModelProvider;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends BaseActivity {

    private CityViewModel mCityViewModel;
    private CityAdapter mCityAdapter;

    private List<City> mProvinceList = new ArrayList<>();

    private Map<String, List<City>> mGroupCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCityViewModel = new ViewModelProvider(this, CustomViewModelProvider.providerCityViewModel())
                .get(CityViewModel.class);
        ((TextView) findViewById(R.id.mTvTitle)).setText("中国");

        try {
            // 从 assets 读取城市数据
            String cityInfo = JsonUtils.getJson("city.json", this);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List<City>> jsonAdapter =
                    moshi.adapter(Types.newParameterizedType(List.class, City.class));
            List<City> cityList = jsonAdapter.fromJson(cityInfo);

            RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mCityAdapter = new CityAdapter(CityType.PROVINCE);
            mCityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(SplashActivity.this, CityListActivity.class);
                    List<City> cities = mGroupCityList.get(mProvinceList.get(position).getProvinceEn());
                    Moshi moshi = new Moshi.Builder().build();
                    JsonAdapter<List<City>> jsonAdapter =
                            moshi.adapter(Types.newParameterizedType(List.class, City.class));
                    String result = jsonAdapter.toJson(cities);
                    intent.putExtra("City", result);
                    intent.putExtra("provinceZh", mProvinceList.get(position).getProvinceZh());
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mCityAdapter);
            // 添加分割线
            DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(decoration);
            mGroupCityList = groupList(cityList);


            for (Map.Entry<String, List<City>> entry : mGroupCityList.entrySet()) {
                mProvinceList.add(entry.getValue().get(0));
            }
            mCityAdapter.setCityList(mProvinceList);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        mCityViewModel.getAllCity().observe(this, new Observer<List<City>>() {
//            @Override
//            public void onChanged(@Nullable final List<City> cityList) {
//                Timber.i("ViewModel：" + cityList.size());
//            }
//        });
    }

    /**
     * 分组
     */
    public Map<String, List<City>> groupList(List<City> cityList) {
        Map<String, List<City>> map = new HashMap<>();
        for (City city : cityList) {
            List<City> tmpList = map.get(city.getProvinceEn());
            if (tmpList == null) {
                tmpList = new ArrayList<>();
                tmpList.add(city);
                map.put(city.getProvinceEn(), tmpList);
            } else {
                tmpList.add(city);
            }
        }
        return map;
    }

}
