package com.seckill.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.data.PM25;
import com.seckill.weather.data.Weather;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Weather weather;
    private PM25 pm25;

    public void setWeather(Weather weather) {
        this.weather = weather;
        notifyDataSetChanged();
    }

    public void setPm25(PM25 pm25) {
        this.pm25 = pm25;
        notifyItemChanged(2);
    }

    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    public static final int TYPE_4 = 3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == TYPE_2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_2, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == TYPE_3) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_3, parent, false);
            return new ViewHolder3(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_4, parent, false);
            return new ViewHolder4(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (weather != null) {
            if (weather.getData() != null && weather.getData().size() > 0) {
                Weather.DataBean todayWeather = weather.getData().get(0);
                if (itemViewType == TYPE_1) {
                    ((ViewHolder1) holder).mTv0.setText(weather.getUpdate_time());
                    ((ViewHolder1) holder).mTv1.setText(todayWeather.getTem() + "℃");
                    ((ViewHolder1) holder).mTv2.setText(todayWeather.getWea());
                } else if (itemViewType == TYPE_2) {
                    RecyclerView smallRecyclerView = ((ViewHolder2) holder).mRecyclerView;
                    smallRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                    WeatherItemAdapter smallAdapter = new WeatherItemAdapter();
                    smallAdapter.setData(weather.getData());
                    smallRecyclerView.setAdapter(smallAdapter);
                } else if (itemViewType == TYPE_3) {
                    ((ViewHolder3) holder).mTv1.setText(todayWeather.getAir());
                } else if (itemViewType == TYPE_4) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (todayWeather.getIndex() != null && todayWeather.getIndex().size() > 0) {
                        for (int i = 0; i < todayWeather.getIndex().size(); i++) {
                            Weather.DataBean.IndexBean indexBean = todayWeather.getIndex().get(i);
                            stringBuilder.append(indexBean.getTitle() + "：");
                            stringBuilder.append(indexBean.getDesc() + "\n");
                        }
                    }
                    ((ViewHolder4) holder).tvDetail.setText(stringBuilder.toString());
                }
            }
        }
        if (pm25 != null && itemViewType == TYPE_3) {
            ((ViewHolder3) holder).mTv2.setText(pm25.getPm25());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else if (position == 2) {
            return TYPE_3;
        } else {
            return TYPE_4;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView mTv0;
        TextView mTv1;
        TextView mTv2;

        public ViewHolder1(View view) {
            super(view);
            mTv0 = view.findViewById(R.id.mTv0);
            mTv1 = view.findViewById(R.id.mTv1);
            mTv2 = view.findViewById(R.id.mTv2);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public ViewHolder2(View view) {
            super(view);
            mRecyclerView = view.findViewById(R.id.mRecyclerView);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        TextView mTv1;
        TextView mTv2;

        public ViewHolder3(View view) {
            super(view);
            mTv1 = view.findViewById(R.id.mTv1);
            mTv2 = view.findViewById(R.id.mTv2);
        }
    }

    static class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView tvDetail;

        public ViewHolder4(View view) {
            super(view);
            tvDetail = view.findViewById(R.id.mTvDetail);
        }
    }

    private RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

}