package com.seckill.weather.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seckill.weather.R;
import com.seckill.weather.adapter.CityAdapter;
import com.seckill.weather.base.BaseActivity;
import com.seckill.weather.data.City;
import com.seckill.weather.db.CityDao;
import com.seckill.weather.db.WeatherDatabase;
import com.seckill.weather.viewmodel.CityViewModel;
import com.seckill.weather.viewmodel.CustomViewModelProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class CollectedCityActivity extends BaseActivity {

    private CityViewModel mCityViewModel;
    private CityAdapter mCityAdapter;

    @BindView(R.id.mTvTitle)
    TextView mTvTitle;
    @BindView(R.id.mIvBack)
    ImageView mIvBack;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);

        mCityViewModel = new ViewModelProvider(this,
                CustomViewModelProvider.providerCityViewModel())
                .get(CityViewModel.class);

        mTvTitle.setText("收藏的城市");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(v -> finish());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityAdapter = new CityAdapter();

        mCityAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(CollectedCityActivity.this, WeatherDetailActivity.class);
            City city = mCityAdapter.getCityList().get(position);
            intent.putExtra("CityId", city.getId());
            intent.putExtra("CityZh", city.getCityZh());
            startActivity(intent);
        });

        mCityAdapter.setOnItemLongClickListener((view, position) -> {
            City city = mCityAdapter.getCityList().get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(CollectedCityActivity.this);
            builder.setTitle("提示");
            builder.setMessage("确认取消收藏" + city.getCityZh() + "吗？");
            builder.setCancelable(true);
            //设置正面按钮
            builder.setPositiveButton("确定", (dialog, which) -> {
                dialog.dismiss();

                Completable.fromAction(() -> {
                    WeatherDatabase database = WeatherDatabase.getDatabase(getApplicationContext());
                    CityDao mCityDao = database.cityDao();
                    mCityDao.uncollect(city.getCityZh());
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        });
            });
            //设置反面按钮
            builder.setNegativeButton("取消", (dialog, which) -> {
                dialog.dismiss();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        mRecyclerView.setAdapter(mCityAdapter);
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mCityViewModel.getCityByCollected().observe(this, cityList -> {
            if (cityList != null && cityList.size() > 0) {
                mCityAdapter.setCityList(cityList);
            }
        });
    }

}
