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

    public LiveData<List<City>> getAllCity() {
        return mRepository.getAllCity();
    }

    public void insert(City city) {
        mRepository.insert(city);
    }
}
