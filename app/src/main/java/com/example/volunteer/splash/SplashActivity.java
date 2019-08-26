package com.example.volunteer.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.volunteer.Activity.LoginActivity;
import com.example.volunteer.R;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class SplashActivity extends AppCompatActivity {

    private BGABanner mBackgroundBanner;
    private BGABanner mForegroundBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initListener();

        processLogic();

    }

    private void initView() {
        mBackgroundBanner = (BGABanner)findViewById(R.id.banner_guide_background);
        mForegroundBanner = (BGABanner)findViewById(R.id.banner_guide_foreground);
    }

    private void initListener() {
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private void processLogic() {
        //设置数据源
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,R.drawable.splash_1,R.drawable.splash_2,R.drawable.splash_3);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
