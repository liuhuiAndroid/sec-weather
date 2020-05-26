package com.seckill.weather.viewmodel;

import com.seckill.weather.viewmodel.factory.CityViewModelFactory;

public class CustomViewModelProvider {

    public static CityViewModelFactory providerCityViewModel() {
        return new CityViewModelFactory();
    }

}
