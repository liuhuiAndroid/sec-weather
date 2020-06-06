package com.seckill.weather.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.seckill.weather.data.City;
import com.seckill.weather.repository.CityRepository;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private CityRepository mRepository;

    public CityViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new CityRepository(application);
    }

    /**
     * 获取所以省份名称
     */
    public LiveData<List<String>> getAllProvince() {
        return mRepository.getAllProvince();
    }

    /**
     * 根据省份名称查询城市列表
     */
    public LiveData<List<City>> getCityByProvince(String provinceZh) {
        return mRepository.getCityByProvince(provinceZh);
    }

    /**
     * 查询已经收藏的城市
     */
    public LiveData<List<City>> getCityByCollected() {
        return mRepository.getCityByCollected();
    }

    public void insertList(List<City> cityList) {
        mRepository.insertList(cityList);
    }

    /**
     * 根据省份名称查询城市列表
     */
    public LiveData<List<City>> getCityByName(String cityName) {
        return mRepository.getCityByName(cityName);
    }
}
