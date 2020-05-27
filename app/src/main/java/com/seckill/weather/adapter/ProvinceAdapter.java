package com.seckill.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.inter.OnItemClickListener;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {

    private List<String> mProvinceList;

    public List<String> getmProvinceList() {
        return mProvinceList;
    }

    public void setProvinceList(List<String> provinceList) {
        this.mProvinceList = provinceList;
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
        view.setOnClickListener(view1 -> {
            if (mRecyclerView != null) {
                // 根据 RecyclerView 获得当前 View 的位置
                int position = mRecyclerView.getChildAdapterPosition(view1);
                onItemClickListener.onClick(view1, position);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cityName.setText(mProvinceList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProvinceList != null) {
            return mProvinceList.size();
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
