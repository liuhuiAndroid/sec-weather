package com.seckill.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.data.City;
import com.seckill.weather.data.CityType;
import com.seckill.weather.inter.OnItemClickListener;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCityList;
    private CityType cityType;

    public CityAdapter(CityType cityType) {
        this.cityType = cityType;
    }

    public void setCityList(List<City> mCityList) {
        this.mCityList = mCityList;
        notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerView != null) {
                    // 根据 RecyclerView 获得当前 View 的位置
                    int position = mRecyclerView.getChildAdapterPosition(view);
                    onItemClickListener.onClick(view, position);
                }
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = mCityList.get(position);
        if (cityType == CityType.CITY) {
            holder.cityName.setText(city.getCityZh());
        } else if (cityType == CityType.PROVINCE) {
            holder.cityName.setText(city.getProvinceZh());
        }
    }

    @Override
    public int getItemCount() {
        if (mCityList != null) {
            return mCityList.size();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;

        public ViewHolder(View view) {
            super(view);
            cityName = view.findViewById(R.id.mTvDetail);
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
