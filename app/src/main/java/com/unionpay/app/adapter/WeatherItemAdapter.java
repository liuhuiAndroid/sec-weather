package com.unionpay.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unionpay.app.R;
import com.unionpay.app.data.Weather;

import java.util.List;

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.ViewHolder>  {

    private List<Weather.DataBean> data;

    public void setData(List<Weather.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_i, parent, false);
        return new WeatherItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather.DataBean weatherBean = data.get(position);
        holder.mTv1.setText(weatherBean.getDate());
        holder.mTv2.setText(weatherBean.getWea());
        holder.mTv3.setText(weatherBean.getTem1());
        holder.mTv4.setText(weatherBean.getTem2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv1;
        TextView mTv2;
        TextView mTv3;
        TextView mTv4;

        public ViewHolder(View view) {
            super(view);
            mTv1 = view.findViewById(R.id.mTv1);
            mTv2 = view.findViewById(R.id.mTv2);
            mTv3 = view.findViewById(R.id.mTv3);
            mTv4 = view.findViewById(R.id.mTv4);
        }
    }

}
