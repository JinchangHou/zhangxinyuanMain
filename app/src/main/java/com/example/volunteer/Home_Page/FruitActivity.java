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
import com.example.volunteer.stack.ScreenManager;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";

    private Toolbar toolbar;
    private ImageView fruitImageView;
    private TextView textView;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
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
        Glide.with(this).load(R.drawable.timg).into(fruitImageView);//设置背景

        String fruitContent=generateFruitContent(fruitName);
        textView.setText("\n" +
                "     志愿者活动感悟     爱，如一池清澈温暖的湖水，寒冬里的一盆燃烧正旺的炭火，夏日炎炎里一 片榕树下的绿阴，慢慢荒漠中的一块绿洲……可能，志愿者这个字眼对于我们已不陌生。他们是时时刻刻出现在每一个贫困的土地上，出现在每一个需要帮助的人群之中。他们用自己的光和热来温暖那些朴实而善良却又饱受磨难人们的心。     “一名志愿者就是一把泥土，但我们存在的意义，不是被淹没，而是与无数把泥土聚集在一起，成就一座山峰、一条山脉、一片群峰。这样的山峰，可以改变风的走向，可以决定水的流速。这风，就是社会风气，这水，就是文明进程。”这句话也生动阐述了志愿服务者的心声。    我们常说志愿服务是一种奉献精神，不求回报。我却想，志愿服务其实是一种付出和回报融于一体的志愿行动。付出的是关爱和行动，获得是快乐和满足。“送人玫瑰，手有余香。”在志愿者的价值追求中，奉献爱心、服务社会，与充实自己、提升自己、感悟人生，变得同等重要，“助人自助”正在成为当代青年倡导的新理念，正成为许多人热衷选择的社会时尚、生活方式甚至生存状态。   志愿者做的不是伟大的事，它是用伟大的爱在做每一件小事，我们帮助那些孩子补习功课，说句实话，确实很辛苦，有的时候也不愿意做，但是，当我们真正的走进那帮孩子们中间的时候，我们的心在感动，也不由自主的联想到我们小的时候。每每回想起通过我们的努力，能使孩子们的学习成绩不断提高，理想志愿更远大了，天真快乐的笑容荡漾在小脸蛋上的时候，我们真是自我陶醉，成就感荡漾在心头。为了孩子们的未来，虽然我们的力量很渺小，但是我们要坚持。看着孩子们手拿着满分的成绩单的那一刻，所有的付出，变成了回报，每一次志愿活动，对我来说就是精神的一种洗礼，是心灵中的净化，我感觉做志愿服务是我一生的追求，我无怨无悔！到新建县去看望聋哑学生，被生命所展示的美深深折服，在无声的世界中却有着对生命倔强的诠释。他们的天真烂漫跟他们的处境\n" +
                "  似乎并不沾边。他们都是一颗颗的珍珠，只是被沙土掩埋的太深。新建敬老院之行，没有想过一个老人会很自愿地住在里面,也许你认为一大群同龄人住在一起应该是快乐的,但那是我们,老年人需要的是温馨的家庭环境,需要家庭的温暖,我听一个同学说老了愿意住在那儿,只是因为那儿的环境,但我想等我们真的老了,我们还会愿意吗?  在去之前以为老人们不会太热情,因为上次去的时候听院长说老人们都挺自闭的,对外界都些许的排斥心理.大多数老人都是农民出身,没什么特别的经历,老人们年纪虽然大了,平常也没什么活动,我们刚到的时候气氛显得还是不怎么活跃,本来精心准备的串词也有很多没用上.敬老院之行让我看到了夕阳也可以发出炽热的光芒，人在暮年也可以做一些有意义的事。人在暮年的时候回忆当年，没有遗憾便是对人生最好的总结。他雄健的书法仿佛在昭示当年的意气，她婉转的语调好似在诉说当年的英姿，每一点每一滴都是对心灵的洗礼。   牵挂是我的责任，奉献是我的志愿，知我志愿，爱我志愿，兴我志愿，是我以后坚定的志愿工作信念。“竹密无妨溪水过，山高不碍白云飞。”相信，在志愿中我定会踏出一条属于自己坚实的路，我会继续的做好志愿服务，履行志愿者的义务，将志愿者的旗帜高高举起。");

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
