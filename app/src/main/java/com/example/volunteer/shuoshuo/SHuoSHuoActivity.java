package com.example.volunteer.shuoshuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SHuoSHuoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private android.os.Handler handler;

    List<Content> contentList=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    contentList= (List<Content>) msg.obj;
//                    Log.d("MainActivity", "contentList.size()==" + contentList.size());
                    ContentAdapter  adapter=new ContentAdapter(contentList,SHuoSHuoActivity.this);
                    recyclerView.setAdapter(adapter);

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_shuo_shuo);
        recyclerView= (RecyclerView) findViewById(R.id.recleView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        requestData();
        Bmob.initialize(this, "00957be3d6e96745186f6a436b6fe2a2");
        requestData();
//        ContentAdapter  adapter=new ContentAdapter(contentList,this);
//        recyclerView.setAdapter(adapter);

        Log.d("MainActivity", contentList.equals(null) + "==" + contentList.size());
//        addData();

        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "into addShuoshuo");
                Intent intent=new Intent(SHuoSHuoActivity.this, AddShuoShuo.class);
//                intent.putExtra("listobj",(Serializable)contentList);
                startActivity(intent);


            }
        });
        findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }

//    private void addData(){
//        List<Comment>list=new ArrayList<>();
//        list.add(new Comment("刘能","你傻啊"));
//        list.add(new Comment("赵四","你好，我叫赵四"));
//
//        final Content content1=new Content("侯金昌",
//                "http://www.ruanyifeng.com/blogimg/asset/2016/bg2016072208.png",
//                "http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/65e5124f40dece4280eebb41355eb6ae.jpg",
//                ".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 ",5,list,new Date().toString(),"罗3炮");
////        final Content content2=new Content("houjinchang","http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/c02cefa6408abc618030f8a1d8aa3ba2.jpg",".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 ",5, list,new Date().toString(),"罗23/9炮");
////        final Content content3=new Content("http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/2c5d69cd40f7b92a804b12b4b7f2c6e2.jpg",".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 ",5, Arrays.asList("pinglun2", "pingluin2", "pinglun2"),new Date().toString(),"罗887炮");
////        final Content content4=new Content("http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/ee9be114400e79c280e941e7ecba33cd.jpg",".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 ",5, Arrays.asList("pinglun2", "pingluin2", "pinglun2"),new Date().toString(),"罗34炮");
////        final Content content5=new Content("http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/cea60a2e40cad06f80794d215c24838f.jpeg",".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 ",5, Arrays.asList("pinglun2", "pingluin2", "pinglun2"),new Date().toString(),"罗45炮");
//        content1.save();
//
////        content2.save();
////        content3.save();
////        content4.save();
////        content5.save();
//    }

    private void requestData(){//利用handler.setMessage()方法传出讯息
        final BmobQuery<Content> query=new BmobQuery<Content>();
        query.order("-createdAt");
//        query.setLimit(5);
        query.findObjects(new FindListener<Content>() {
            @Override
            public void done(List<Content> list, BmobException e) {
                if (e == null) {
//                    Log.d("MainActivity", "requestData()" + "list.size==" + list.size());
                    Message message = handler.obtainMessage();
                    message.what = 0;
                    //以消息为载体
                    message.obj = list;//查询出的lsit
                    handler.sendMessage(message);
                }
            }
        });
    }
}
