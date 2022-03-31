package com.unionpay.app.utilities;


import android.content.Context;

import com.unionpay.app.data.City;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

public class ConfigUtil {

    public static List<City> getCityList(Context context) {
        try {
            // 从 assets 读取城市数据
            String cityInfo = AssetsUtil.getJson("city.json", context);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List<City>> jsonAdapter =
                    moshi.adapter(Types.newParameterizedType(List.class, City.class));
            List<City> cityList = jsonAdapter.fromJson(cityInfo);
            return cityList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
