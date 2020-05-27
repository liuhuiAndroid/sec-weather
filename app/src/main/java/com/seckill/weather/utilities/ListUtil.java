package com.seckill.weather.utilities;

import com.seckill.weather.data.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {

    /**
     * 对城市列表进行分组
     */
    public static Map<String, List<City>> cityListToGroup(List<City> cityList) {
        Map<String, List<City>> map = new HashMap<>();
        for (City city : cityList) {
            List<City> tmpList = map.get(city.getProvinceEn());
            if (tmpList == null) {
                tmpList = new ArrayList<>();
                tmpList.add(city);
                map.put(city.getProvinceEn(), tmpList);
            } else {
                tmpList.add(city);
            }
        }
        return map;
    }
}
