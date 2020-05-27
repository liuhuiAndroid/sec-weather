package com.seckill.weather.viewmodel;

import com.seckill.weather.viewmodel.factory.CityViewModelFactory;
import com.seckill.weather.viewmodel.factory.WeatherDetailViewModelFactory;

public class CustomViewModelProvider {

    public static CityViewModelFactory providerCityViewModel() {
        return new CityViewModelFactory();
    }

    public static WeatherDetailViewModelFactory providerWeatherDetailViewModel(Long cityId) {
        return new WeatherDetailViewModelFactory(cityId);
    }

}
