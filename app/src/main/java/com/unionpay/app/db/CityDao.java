package com.unionpay.app.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unionpay.app.data.City;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insertAll(List<City> cityList);

    @Query("select province_zh FROM city group by province_zh")
    LiveData<List<String>> queryProvinceZh();

    @Query("select * FROM city WHERE province_zh = :provinceZh")
    LiveData<List<City>> queryCityByProvinceZh(String provinceZh);

    @Query("select * FROM city WHERE city_zh = :cityZh")
    City queryCityByCityZh(String cityZh);

    @Query("select * FROM city WHERE city_zh like '%' || :cityName || '%'")
    LiveData<List<City>> getCityByName(String cityName);

    @Query("select * FROM city WHERE collect == 1")
    LiveData<List<City>> getCityByCollected();

    @Query("update city set collect=1 where id=:cityId")
    void collect(Long cityId);

    @Query("update city set collect=0 where id=:cityId")
    void uncollect(Long cityId);
}
