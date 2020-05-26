package com.seckill.weather.data;

import java.util.List;

public class Weather {

    private String cityid;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private String update_time;
    private AqiBean aqi;
    private List<DataBean> data;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class AqiBean {
        /**
         * air : 80
         * air_level : 良
         * air_tips : 空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！
         * pm25 : 8
         * pm25_desc : 优
         * pm10 : 16
         * pm10_desc : 优
         * o3 : 119
         * o3_desc : 良
         * no2 : 4
         * no2_desc : 优
         * so2 : 10
         * so2_desc : 优
         * kouzhao : 无需戴口罩
         * waichu : 适宜外出
         * kaichuang : 适宜开窗
         * jinghuaqi : 关闭净化器
         * cityid : 101130105
         * city : 达坂城
         * cityEn : dabancheng
         * country : 中国
         * countryEn : China
         */

        private String air;
        private String air_level;
        private String air_tips;
        private String pm25;
        private String pm25_desc;
        private String pm10;
        private String pm10_desc;
        private String o3;
        private String o3_desc;
        private String no2;
        private String no2_desc;
        private String so2;
        private String so2_desc;
        private String kouzhao;
        private String waichu;
        private String kaichuang;
        private String jinghuaqi;
        private String cityid;
        private String city;
        private String cityEn;
        private String country;
        private String countryEn;

        public String getAir() {
            return air;
        }

        public void setAir(String air) {
            this.air = air;
        }

        public String getAir_level() {
            return air_level;
        }

        public void setAir_level(String air_level) {
            this.air_level = air_level;
        }

        public String getAir_tips() {
            return air_tips;
        }

        public void setAir_tips(String air_tips) {
            this.air_tips = air_tips;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm25_desc() {
            return pm25_desc;
        }

        public void setPm25_desc(String pm25_desc) {
            this.pm25_desc = pm25_desc;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm10_desc() {
            return pm10_desc;
        }

        public void setPm10_desc(String pm10_desc) {
            this.pm10_desc = pm10_desc;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO3_desc() {
            return o3_desc;
        }

        public void setO3_desc(String o3_desc) {
            this.o3_desc = o3_desc;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNo2_desc() {
            return no2_desc;
        }

        public void setNo2_desc(String no2_desc) {
            this.no2_desc = no2_desc;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getSo2_desc() {
            return so2_desc;
        }

        public void setSo2_desc(String so2_desc) {
            this.so2_desc = so2_desc;
        }

        public String getKouzhao() {
            return kouzhao;
        }

        public void setKouzhao(String kouzhao) {
            this.kouzhao = kouzhao;
        }

        public String getWaichu() {
            return waichu;
        }

        public void setWaichu(String waichu) {
            this.waichu = waichu;
        }

        public String getKaichuang() {
            return kaichuang;
        }

        public void setKaichuang(String kaichuang) {
            this.kaichuang = kaichuang;
        }

        public String getJinghuaqi() {
            return jinghuaqi;
        }

        public void setJinghuaqi(String jinghuaqi) {
            this.jinghuaqi = jinghuaqi;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityEn() {
            return cityEn;
        }

        public void setCityEn(String cityEn) {
            this.cityEn = cityEn;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryEn() {
            return countryEn;
        }

        public void setCountryEn(String countryEn) {
            this.countryEn = countryEn;
        }
    }

    public static class DataBean {
        /**
         * day : 26日（星期二）
         * date : 2020-05-26
         * week : 星期二
         * wea : 晴
         * wea_img : qing
         * wea_day : 晴
         * wea_day_img : qing
         * wea_night : 晴
         * wea_night_img : qing
         * tem : 22
         * tem1 : 28
         * tem2 : 13
         * humidity : 21%
         * visibility : 14.29km
         * pressure : 893
         * win : ["北风","无持续风向"]
         * win_speed : <3级
         * win_meter : 小于12km/h
         * sunrise : 06:33
         * sunset : 21:34
         * air : 40
         * air_level : 优
         * air_tips : 空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！
         * alarm : {"alarm_type":"","alarm_level":"","alarm_content":""}
         * hours : [{"hours":"22时","wea":"晴","wea_img":"qing","tem":"21","win":"西南风","win_speed":"<3级"},{"hours":"23时","wea":"晴","wea_img":"qing","tem":"19","win":"无持续风向","win_speed":"<3级"},{"hours":"00时","wea":"晴","wea_img":"qing","tem":"18","win":"南风","win_speed":"<3级"},{"hours":"01时","wea":"晴","wea_img":"qing","tem":"16","win":"东风","win_speed":"<3级"},{"hours":"02时","wea":"晴","wea_img":"qing","tem":"15","win":"无持续风向","win_speed":"<3级"},{"hours":"03时","wea":"晴","wea_img":"qing","tem":"14","win":"东北风","win_speed":"<3级"},{"hours":"04时","wea":"晴","wea_img":"qing","tem":"13","win":"东北风","win_speed":"<3级"},{"hours":"05时","wea":"晴","wea_img":"qing","tem":"12","win":"无持续风向","win_speed":"<3级"},{"hours":"06时","wea":"晴","wea_img":"qing","tem":"13","win":"东北风","win_speed":"<3级"},{"hours":"07时","wea":"晴","wea_img":"qing","tem":"13","win":"东风","win_speed":"<3级"}]
         * index : [{"title":"紫外线指数","level":"很强","desc":"涂擦SPF20以上，PA++护肤品，避强光。"},{"title":"减肥指数","level":"五颗星","desc":"夏天悄然到，肉已无处藏。天气较舒适，快去运动吧。"},{"title":"血糖指数","level":"易波动","desc":"血糖易波动，注意监测。"},{"title":"穿衣指数","level":"舒适","desc":"建议穿长袖衬衫单裤等服装。"},{"title":"洗车指数","level":"适宜","desc":"天气较好，适合擦洗汽车。"},{"title":"空气污染扩散指数","level":"中","desc":"易感人群应适当减少室外活动。"}]
         */

        private String day;
        private String date;
        private String week;
        private String wea;
        private String wea_img;
        private String wea_day;
        private String wea_day_img;
        private String wea_night;
        private String wea_night_img;
        private String tem;
        private String tem1;
        private String tem2;
        private String humidity;
        private String visibility;
        private String pressure;
        private String win_speed;
        private String win_meter;
        private String sunrise;
        private String sunset;
        private String air;
        private String air_level;
        private String air_tips;
        private AlarmBean alarm;
        private List<String> win;
        private List<HoursBean> hours;
        private List<IndexBean> index;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWea() {
            return wea;
        }

        public void setWea(String wea) {
            this.wea = wea;
        }

        public String getWea_img() {
            return wea_img;
        }

        public void setWea_img(String wea_img) {
            this.wea_img = wea_img;
        }

        public String getWea_day() {
            return wea_day;
        }

        public void setWea_day(String wea_day) {
            this.wea_day = wea_day;
        }

        public String getWea_day_img() {
            return wea_day_img;
        }

        public void setWea_day_img(String wea_day_img) {
            this.wea_day_img = wea_day_img;
        }

        public String getWea_night() {
            return wea_night;
        }

        public void setWea_night(String wea_night) {
            this.wea_night = wea_night;
        }

        public String getWea_night_img() {
            return wea_night_img;
        }

        public void setWea_night_img(String wea_night_img) {
            this.wea_night_img = wea_night_img;
        }

        public String getTem() {
            return tem;
        }

        public void setTem(String tem) {
            this.tem = tem;
        }

        public String getTem1() {
            return tem1;
        }

        public void setTem1(String tem1) {
            this.tem1 = tem1;
        }

        public String getTem2() {
            return tem2;
        }

        public void setTem2(String tem2) {
            this.tem2 = tem2;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getWin_speed() {
            return win_speed;
        }

        public void setWin_speed(String win_speed) {
            this.win_speed = win_speed;
        }

        public String getWin_meter() {
            return win_meter;
        }

        public void setWin_meter(String win_meter) {
            this.win_meter = win_meter;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getAir() {
            return air;
        }

        public void setAir(String air) {
            this.air = air;
        }

        public String getAir_level() {
            return air_level;
        }

        public void setAir_level(String air_level) {
            this.air_level = air_level;
        }

        public String getAir_tips() {
            return air_tips;
        }

        public void setAir_tips(String air_tips) {
            this.air_tips = air_tips;
        }

        public AlarmBean getAlarm() {
            return alarm;
        }

        public void setAlarm(AlarmBean alarm) {
            this.alarm = alarm;
        }

        public List<String> getWin() {
            return win;
        }

        public void setWin(List<String> win) {
            this.win = win;
        }

        public List<HoursBean> getHours() {
            return hours;
        }

        public void setHours(List<HoursBean> hours) {
            this.hours = hours;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public static class AlarmBean {
            /**
             * alarm_type :
             * alarm_level :
             * alarm_content :
             */

            private String alarm_type;
            private String alarm_level;
            private String alarm_content;

            public String getAlarm_type() {
                return alarm_type;
            }

            public void setAlarm_type(String alarm_type) {
                this.alarm_type = alarm_type;
            }

            public String getAlarm_level() {
                return alarm_level;
            }

            public void setAlarm_level(String alarm_level) {
                this.alarm_level = alarm_level;
            }

            public String getAlarm_content() {
                return alarm_content;
            }

            public void setAlarm_content(String alarm_content) {
                this.alarm_content = alarm_content;
            }
        }

        public static class HoursBean {
            /**
             * hours : 22时
             * wea : 晴
             * wea_img : qing
             * tem : 21
             * win : 西南风
             * win_speed : <3级
             */

            private String hours;
            private String wea;
            private String wea_img;
            private String tem;
            private String win;
            private String win_speed;

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getWea() {
                return wea;
            }

            public void setWea(String wea) {
                this.wea = wea;
            }

            public String getWea_img() {
                return wea_img;
            }

            public void setWea_img(String wea_img) {
                this.wea_img = wea_img;
            }

            public String getTem() {
                return tem;
            }

            public void setTem(String tem) {
                this.tem = tem;
            }

            public String getWin() {
                return win;
            }

            public void setWin(String win) {
                this.win = win;
            }

            public String getWin_speed() {
                return win_speed;
            }

            public void setWin_speed(String win_speed) {
                this.win_speed = win_speed;
            }
        }

        public static class IndexBean {
            /**
             * title : 紫外线指数
             * level : 很强
             * desc : 涂擦SPF20以上，PA++护肤品，避强光。
             */

            private String title;
            private String level;
            private String desc;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}
