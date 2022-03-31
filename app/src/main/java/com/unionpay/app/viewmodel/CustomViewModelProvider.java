package com.unionpay.app.viewmodel;

import com.unionpay.app.viewmodel.factory.CityViewModelFactory;
import com.unionpay.app.viewmodel.factory.WeatherDetailViewModelFactory;

public class CustomViewModelProvider {

    public static CityViewModelFactory providerCityViewModel() {
        return new CityViewModelFactory();
    }

    public static WeatherDetailViewModelFactory providerWeatherDetailViewModel(Long cityId) {
        return new WeatherDetailViewModelFactory(cityId);
    }

}
