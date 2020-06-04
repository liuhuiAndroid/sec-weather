package com.seckill.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.ProvinceAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.utilities.ConfigUtil;
import com.seckill.weather.viewmodel.CityViewModel;
import com.seckill.weather.viewmodel.CustomViewModelProvider;

import timber.log.Timber;

public class ProvinceListActivity extends BaseActivity {

    private CityViewModel mCityViewModel;
    private ProvinceAdapter mProvinceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        mCityViewModel = new ViewModelProvider(this,
                CustomViewModelProvider.providerCityViewModel())
                .get(CityViewModel.class);

        // 设置标题
        ((TextView) findViewById(R.id.mTvTitle)).setText("中国");
        findViewById(R.id.mIvSearch).setVisibility(View.VISIBLE);
        findViewById(R.id.mIvSearch).setOnClickListener(v -> {
            Intent intent = new Intent(ProvinceListActivity.this, SearchCityListActivity.class);
            startActivity(intent);
        });

        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProvinceAdapter = new ProvinceAdapter();
        mProvinceAdapter.setOnItemClickListener((view, position) -> {
            // 跳转城市列表页面
            Intent intent = new Intent(ProvinceListActivity.this, CityListActivity.class);
            intent.putExtra("provinceZh", mProvinceAdapter.getmProvinceList().get(position));
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mProvinceAdapter);
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        loadData();
    }

    private void loadData() {
        mCityViewModel.getAllProvince().observe(this, provinceList -> {
            if (provinceList != null && provinceList.size() > 0) {
                Timber.i("数据库获取到省份数据：" + provinceList.size());
                mProvinceAdapter.setProvinceList(provinceList);
            } else {
                Timber.i("数据库没有获取到省份数据");
                // 从资源文件获取数据保存到数据库
                mCityViewModel.insertList(ConfigUtil.getCityList(this));
            }
        });
    }

}
