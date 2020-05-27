package com.seckill.weather.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.seckill.weather.Constants;
import com.seckill.weather.data.City;

@Database(entities = {City.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static volatile WeatherDatabase INSTANCE;

    public static WeatherDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    WeatherDatabase.class, Constants.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CityDao cityDao();
}
