package com.seckill.weather.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.seckill.weather.WeatherApp;
import com.seckill.weather.viewmodel.CityViewModel;
import com.seckill.weather.viewmodel.WeatherDetailViewModel;

public class WeatherDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Long cityId;

    public WeatherDetailViewModelFactory(Long cityId) {
        this.cityId = cityId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherDetailViewModel(cityId);
    }
}
