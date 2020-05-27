package com.seckill.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.CityAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.data.CityType;
import com.seckill.weather.inter.OnItemClickListener;
import com.seckill.weather.utilities.ConfigUtil;
import com.seckill.weather.utilities.ListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProvinceListActivity extends BaseActivity {

    private CityAdapter mCityAdapter;
    // 省份列表
    private List<City> mProvinceList = new ArrayList<>();
    // 省市数据
    private Map<String, List<City>> mCityGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        // 设置标题
        ((TextView) findViewById(R.id.mTvTitle)).setText("中国");
        // 从 assets 读取城市数据
        List<City> cityList = ConfigUtil.getCityList(this);
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityAdapter = new CityAdapter(CityType.PROVINCE);
        mCityAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // 跳转城市列表页面
                Intent intent = new Intent(ProvinceListActivity.this, CityListActivity.class);
                List<City> cityListData = mCityGroupList.get(mProvinceList.get(position).getProvinceEn());
                intent.putExtra("provinceZh", mProvinceList.get(position).getProvinceZh());
                intent.putExtra("cityList", (Serializable) cityListData);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mCityAdapter);
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 对城市列表进行分组
        mCityGroupList = ListUtil.cityListToGroup(cityList);
        for (Map.Entry<String, List<City>> entry : mCityGroupList.entrySet()) {
            // 每个省挑出一个城市作为省列表数据
            mProvinceList.add(entry.getValue().get(0));
        }
        mCityAdapter.setCityList(mProvinceList);
    }

}
