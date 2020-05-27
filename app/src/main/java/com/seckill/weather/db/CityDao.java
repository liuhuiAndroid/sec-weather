package com.seckill.weather.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.seckill.weather.data.City;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insert(City city);

    @Insert
    void insertAll(List<City> cityList);

    @Query("SELECT * FROM city")
    LiveData<List<City>> queryAll();

    @Query("select province_zh FROM city group by province_zh")
    LiveData<List<String>> queryProvinceZh();

    @Query("select * FROM city WHERE province_zh = :provinceZh")
    LiveData<List<City>> queryCityByProvinceZh(String provinceZh);

}
