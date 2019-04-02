package com.example.volunteer.personal_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PersonPage extends AppCompatActivity {
    public static final int CHANGE_TYPE_STU_NAME = 1;
    public static final int CHANGE_TYPE_SCHOOL = 2;
    public static final int CHANGE_TYPE_MOBILE = 3;
    public static final int CHANGE_TYPE_EMAIL = 4;

    private ImageView mHBack;
    private ImageView mHHead;
    private ImageView mUserLine;
    private TextView mUserName;
    private TextView mUserVal;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_person_page);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将ToolBar实例传入
        initView();
        setData();
    }

    //加载toolbar文件


    //把toolbar中的item导入，，，插入到toolBar中，R.menu.toolbar中间都是menuRes资源

    private void setData() {

        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.images)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(mHBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.images)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mHHead);

        mUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PersonPage.this,ChangeMessageActivity.class);
                intent.putExtra("changeType",CHANGE_TYPE_STU_NAME);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }

    private void initView() {
        //顶部头像控件
        mHBack = (ImageView) findViewById(R.id.h_back);
        mHHead = (ImageView) findViewById(R.id.h_head);
        mUserLine = (ImageView) findViewById(R.id.user_line);
        mUserName = (TextView) findViewById(R.id.user_name);
        mUserVal = (TextView) findViewById(R.id.user_val);

    }
}
