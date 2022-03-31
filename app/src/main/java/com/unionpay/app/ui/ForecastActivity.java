package com.unionpay.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.unionpay.app.R;
import com.unionpay.app.base.BaseActivity;
import com.unionpay.app.viewmodel.ForecastViewModel;
import com.unionpay.unionpaysdk.UnionpaySDKManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ForecastActivity extends BaseActivity {

    private ForecastViewModel mForecastViewModel;

    @BindView(R.id.mTvTitle)
    TextView mTvTitle;
    @BindView(R.id.mImageView)
    ImageView mImageView;
    @BindView(R.id.mTvChooseCity)
    AppCompatButton mTvChooseCity;
    @BindView(R.id.mTvCollect)
    AppCompatButton mTvCollect;
    @BindView(R.id.mTvTest)
    AppCompatButton mTvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        mForecastViewModel = new ViewModelProvider(this)
                .get(ForecastViewModel.class);
        mTvTitle.setText("全国天气降水量预报图");

        mTvChooseCity.setOnClickListener(v ->
                startActivity(new Intent(ForecastActivity.this, ProvinceListActivity.class)));
        mTvCollect.setOnClickListener(v ->
                startActivity(new Intent(ForecastActivity.this, CollectedCityActivity.class)));
        mTvTest.setOnClickListener(v -> {
            UnionpaySDKManager.getInstance(this).startScanCode();
        });
        loadData();
        bindUser();
    }

    private void bindUser() {
        Bundle bundle = new Bundle();
        bundle.putString("sn", "@app@02d12e6a2002005b001");
        UnionpaySDKManager.getInstance(this).bindUser(bundle, new UnionpaySDKManager.IUnionCallback() {
            @Override
            public void onResult(Bundle bundle) {
                Toast.makeText(ForecastActivity.this, "bindUser success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String s, String s1) {
                Toast.makeText(ForecastActivity.this, "bindUser error: " + s + s1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        mForecastViewModel.getForecastLiveData().observe(this, forecast -> {
            Glide.with(ForecastActivity.this)
                    .load(forecast.getData().get(0).getPic())
                    .into(mImageView);
        });
    }
}
