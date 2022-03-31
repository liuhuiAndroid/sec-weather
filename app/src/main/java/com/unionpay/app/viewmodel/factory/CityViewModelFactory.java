package com.unionpay.app.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.unionpay.app.WeatherApp;
import com.unionpay.app.viewmodel.CityViewModel;

public class CityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CityViewModel(WeatherApp.mContext);
    }
}
