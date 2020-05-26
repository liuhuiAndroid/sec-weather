package com.seckill.weather.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.seckill.weather.data.City;
import com.seckill.weather.db.CityDao;
import com.seckill.weather.db.WeatherDatabase;

import java.util.List;

public class CityRepository {

    private CityDao mCityDao;
    private LiveData<List<City>> mAllCity;

    public CityRepository(Application application) {
        WeatherDatabase db = WeatherDatabase.getDatabase(application);
        mCityDao = db.cityDao();
        mAllCity = mCityDao.queryAll();
    }

    public LiveData<List<City>> getAllCity() {
        return mAllCity;
    }

    public void insert(City city) {
        new InsertAsyncTask(mCityDao).execute(city);
    }

    private static class InsertAsyncTask extends AsyncTask<City, Void, Void> {
        private CityDao mAsyncDao;

        InsertAsyncTask(CityDao wordDao) {
            this.mAsyncDao = wordDao;
        }

        @Override
        protected Void doInBackground(City... words) {
            mAsyncDao.insert(words[0]);
            return null;
        }
    }
}
