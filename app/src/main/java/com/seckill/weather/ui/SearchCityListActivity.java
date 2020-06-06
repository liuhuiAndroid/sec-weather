package com.seckill.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.CityAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.viewmodel.CityViewModel;
import com.seckill.weather.viewmodel.CustomViewModelProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCityListActivity extends BaseActivity {

    private CityViewModel mCityViewModel;
    private CityAdapter mCityAdapter;

    @BindView(R.id.mEtSearch)
    EditText mEtSearch;
    @BindView(R.id.mIvBack)
    ImageView mIvBack;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city_list);
        ButterKnife.bind(this);

        mCityViewModel = new ViewModelProvider(this,
                CustomViewModelProvider.providerCityViewModel())
                .get(CityViewModel.class);

        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(v -> finish());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityAdapter = new CityAdapter();

        mCityAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(SearchCityListActivity.this, WeatherDetailActivity.class);
            City city = mCityAdapter.getCityList().get(position);
            intent.putExtra("CityId", city.getId());
            intent.putExtra("CityZh", city.getCityZh());
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mCityAdapter);
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cityName = mEtSearch.getText().toString();
                if (!TextUtils.isEmpty(cityName)) {
                    mCityViewModel.getCityByName(cityName).observe(SearchCityListActivity.this, cityList -> {
                        if (cityList != null && cityList.size() > 0) {
                            mCityAdapter.setCityList(cityList);
                        }
                    });
                } else {
                    mCityAdapter.setCityList(new ArrayList<>());
                }
            }
        });
    }
}
