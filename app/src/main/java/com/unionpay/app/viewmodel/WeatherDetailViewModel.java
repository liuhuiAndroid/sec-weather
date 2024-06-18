package com.unionpay.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.unionpay.app.Constants;
import com.unionpay.app.data.PM25;
import com.unionpay.app.data.Weather;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class WeatherDetailViewModel extends ViewModel {

    private MutableLiveData<Weather> weatherLiveData;
    private MutableLiveData<PM25> pm25LiveData;

    private Long cityId;

    public WeatherDetailViewModel(Long cityId) {
        this.cityId = cityId;
    }

    public LiveData<Weather> getWeatherLiveData() {
        if (weatherLiveData == null) {
            weatherLiveData = new MutableLiveData<>();
            loadWeather(cityId);
        }
        return weatherLiveData;
    }

    public LiveData<PM25> getPm25LiveData() {
        if (pm25LiveData == null) {
            pm25LiveData = new MutableLiveData<>();
            loadPM25(cityId);
        }
        return pm25LiveData;
    }

    /**
     * 根据城市查询天气详细信息接口
     */
    private void loadWeather(Long cityId) {
        OkGo.<String>get(Constants.BASE_URL)
                .params("appid", Constants.APP_ID)
                .params("appsecret", Constants.APP_SECRET)
                .params("version", "v9")
                .params("cityid", cityId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Moshi moshi = new Moshi.Builder().build();
                            JsonAdapter<Weather> jsonAdapter = moshi.adapter(Weather.class);
                            Weather weather = jsonAdapter.fromJson(response.body());
                            weatherLiveData.setValue(weather);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 根据城市查询空气质量指数 PM2.5 污染物
     */
    private void loadPM25(Long cityId) {
        OkGo.<String>get(Constants.BASE_URL1)
                .params("appid", Constants.APP_ID)
                .params("appsecret", Constants.APP_SECRET)
                .params("version", "v10")
                .params("cityid", cityId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Moshi moshi = new Moshi.Builder().build();
                            JsonAdapter<PM25> jsonAdapter = moshi.adapter(PM25.class);
                            PM25 pm25 = jsonAdapter.fromJson(response.body());
                            pm25LiveData.setValue(pm25);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
