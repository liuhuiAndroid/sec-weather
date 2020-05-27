package com.seckill.weather.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.seckill.weather.data.City;
import com.seckill.weather.db.CityDao;
import com.seckill.weather.db.WeatherDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class CityRepository {

    private CityDao mCityDao;
    private LiveData<List<String>> mAllProvinceLiveData;
    private LiveData<List<City>> mCityByProvinceLiveData;

    public CityRepository(Application application) {
        WeatherDatabase database = WeatherDatabase.getDatabase(application);
        mCityDao = database.cityDao();
    }

    public LiveData<List<String>> getAllProvince() {
        if (mAllProvinceLiveData == null) {
            mAllProvinceLiveData = mCityDao.queryProvinceZh();
        }
        return mAllProvinceLiveData;
    }

    public LiveData<List<City>> getCityByProvince(String provinceZh) {
        if (mCityByProvinceLiveData == null) {
            mCityByProvinceLiveData = mCityDao.queryCityByProvinceZh(provinceZh);
        }
        return mCityByProvinceLiveData;
    }

    public void insertList(List<City> cityList) {
        // 使用 RxJava 切换到后台线程给数据库插入数据
        Completable.fromAction(() -> mCityDao.insertAll(cityList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Timber.i("inset city list complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Timber.i("inset city list error");
                    }
                });
    }

}
