package com.seckill.weather.data;

import java.util.List;

public class Forecast {

    private String update_time;
    private String date;
    private List<DataBean> data;

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{

        private String title;
        private String pic;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "update_time='" + update_time + '\'' +
                ", date='" + date + '\'' +
                ", data=" + data +
                '}';
    }
}
