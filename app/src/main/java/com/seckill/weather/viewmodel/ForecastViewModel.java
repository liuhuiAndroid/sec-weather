package com.seckill.weather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.seckill.weather.Constants;
import com.seckill.weather.data.Forecast;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class ForecastViewModel extends ViewModel {

    private MutableLiveData<Forecast> forecastLiveData;

    public LiveData<Forecast> getForecastLiveData() {
        if (forecastLiveData == null) {
            forecastLiveData = new MutableLiveData<>();
            loadForecast();
        }
        return forecastLiveData;
    }

    /**
     * 根据城市查询天气详细信息接口
     */
    private void loadForecast() {
        OkGo.<String>get(Constants.BASE_URL)
                .params("appid", Constants.APP_ID)
                .params("appsecret", Constants.APP_SECRET)
                .params("version", "v8")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Moshi moshi = new Moshi.Builder().build();
                            JsonAdapter<Forecast> jsonAdapter = moshi.adapter(Forecast.class);
                            Forecast forecast = jsonAdapter.fromJson(response.body());
                            forecastLiveData.setValue(forecast);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
