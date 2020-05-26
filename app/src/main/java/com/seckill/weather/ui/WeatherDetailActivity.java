package com.seckill.weather.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.seckill.weather.Constants;
import com.seckill.weather.R;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.Weather;

import timber.log.Timber;

public class WeatherDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        long cityId = getIntent().getLongExtra("CityId", -1);
        String cityZh = getIntent().getStringExtra("CityZh");
        ((TextView) findViewById(R.id.mTvTitle)).setText(cityZh + "天气");

        OkGo.<Weather>get(Constants.BASE_URL)
                .params("appid", Constants.APP_ID)
                .params("appsecret", Constants.APP_SECRET)
                .params("version", "v9")
                .params("cityid", cityId)
                .execute(new Callback<Weather>() {
                    @Override
                    public Weather convertResponse(okhttp3.Response response) throws Throwable {
                        return null;
                    }

                    @Override
                    public void onStart(Request<Weather, ? extends Request> request) {
                        Timber.i("onStart:");
                    }

                    @Override
                    public void onSuccess(Response<Weather> response) {
                        Timber.i("Weather:" + response);
                    }

                    @Override
                    public void onCacheSuccess(Response<Weather> response) {
                        Timber.i("onCacheSuccess:");
                    }

                    @Override
                    public void onError(Response<Weather> response) {
                        Timber.i("onError:");

                    }

                    @Override
                    public void onFinish() {
                        Timber.i("onFinish:");
                    }

                    @Override
                    public void uploadProgress(Progress progress) {

                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                    }
                });
    }

}
