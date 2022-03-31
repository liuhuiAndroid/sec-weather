package com.unionpay.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unionpay.app.R;
import com.unionpay.app.adapter.WeatherAdapter;
import com.unionpay.app.base.BaseActivity;
import com.unionpay.app.data.City;
import com.unionpay.app.db.CityDao;
import com.unionpay.app.db.WeatherDatabase;
import com.unionpay.app.viewmodel.CustomViewModelProvider;
import com.unionpay.app.viewmodel.WeatherDetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherDetailActivity extends BaseActivity {

    private WeatherDetailViewModel mWeatherDetailViewModel;
    private WeatherAdapter mWeatherAdapter;

    @BindView(R.id.mTvTitle)
    TextView mTvTitle;
    @BindView(R.id.mIvBack)
    ImageView mIvBack;
    @BindView(R.id.mIvRight)
    ImageView mIvRight;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        long cityId = getIntent().getLongExtra("CityId", -1);
        mWeatherDetailViewModel = new ViewModelProvider(this,
                CustomViewModelProvider.providerWeatherDetailViewModel(cityId))
                .get(WeatherDetailViewModel.class);
        String cityZh = getIntent().getStringExtra("CityZh");
        mTvTitle.setText(cityZh);

        Completable.fromAction(() -> {
            WeatherDatabase database = WeatherDatabase.getDatabase(getApplicationContext());
            CityDao mCityDao = database.cityDao();
            City city = mCityDao.queryCityByCityZh(cityZh);
            if (city.getCollect() == 1) {
                mIvRight.setVisibility(View.GONE);
            } else {
                mIvRight.setVisibility(View.VISIBLE);
            }
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
                    }
                });
        mIvRight.setImageResource(R.mipmap.ic_collected);
        mIvRight.setOnClickListener(v -> {
            // 使用 RxJava 切换到后台线程给数据库插入数据
            Completable.fromAction(() -> {
                WeatherDatabase database = WeatherDatabase.getDatabase(getApplicationContext());
                CityDao mCityDao = database.cityDao();
                mCityDao.collect(cityId);
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(WeatherDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            mIvRight.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Timber.i("inset city list error");
                        }
                    });
        });

        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(v -> finish());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeatherAdapter = new WeatherAdapter();
        mRecyclerView.setAdapter(mWeatherAdapter);

        loadData();
    }

    private void loadData() {
        mWeatherDetailViewModel.getWeatherLiveData().observe(this,
                weather -> mWeatherAdapter.setWeather(weather));
        mWeatherDetailViewModel.getPm25LiveData().observe(this,
                pm25 -> mWeatherAdapter.setPm25(pm25));
    }

}
