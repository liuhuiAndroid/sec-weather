package com.seckill.weather.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.seckill.weather.WeatherApp;
import com.seckill.weather.viewmodel.CityViewModel;

public class CityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CityViewModel(WeatherApp.mContext);
    }
}
