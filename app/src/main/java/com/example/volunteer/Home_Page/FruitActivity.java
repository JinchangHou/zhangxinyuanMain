package com.example.volunteer.Home_Page;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.volunteer.R;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";

    private Toolbar toolbar;
    private ImageView fruitImageView;
    private TextView textView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);



        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitNameId=intent.getIntExtra(FRUIT_IMAGE_ID,0);


        toolbar= (Toolbar) findViewById(R.id.toolbar);
        fruitImageView= (ImageView) findViewById(R.id.fruit_image_view);
        textView= (TextView) findViewById(R.id.fruit_content_text);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);//设置bar
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();//获取actionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//bar中设置默认返回按钮箭头
        }

        collapsingToolbarLayout.setTitle(fruitName);//设置标题
//        Log.d("123", fruitNameId);
        Glide.with(this).load(fruitNameId).into(fruitImageView);//设置背景

        String fruitContent=generateFruitContent(fruitName);
        textView.setText(fruitContent);

    }

    private String generateFruitContent(String fruitName){//生成内容
        StringBuilder fruitContent=new StringBuilder();
        for(int i=0;i<500;i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    //处理homeAsUp的点击事件，因为前面设置了actionBar.setDisplayHomeAsUpEnabled(true);
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();//关闭当前活动
                return true;
        }return super.onOptionsItemSelected(item);
    }
}
