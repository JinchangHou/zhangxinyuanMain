package com.example.volunteer.task;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.volunteer.Javabean.Task;
import com.example.volunteer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class GetTaskActivity extends AppCompatActivity {
    private RecyclerView taskRecyView;
    public TaskAdapter adapter;
    private static Context instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_task2);

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("action.refreshTask");
        registerReceiver(mRefreshBroadcastReceiver,intentFilter);

        taskRecyView=findViewById(R.id.task_recyclerView);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        taskRecyView.setLayoutManager(layoutManager);

//        addData();
        requestData();
        instance=this;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //设置刷新操作
//        Toast.makeText(this,"restart",Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }

    public static Context getInstance(){
        return instance;
    }




    private BroadcastReceiver mRefreshBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("广播","广播成功");
           //如何在广播接收器中实现界面刷新

        }
    };



    private void requestData(){
        final BmobQuery<Task> query=new BmobQuery<Task>();
        query.order("-createdAt");
//        query.setLimit(5);
        query.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {

                if(e==null){
                    adapter=new TaskAdapter(GetTaskActivity.this,list);
                    taskRecyView.setAdapter(adapter);
                    Log.d("fuck","s");
                }
                else{

                }
            }
        });
    }
    private void addData(){
//        List<Task> list=new ArrayList<>();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//多态

        Task task=new Task(bf.format(new Date()).toString(),"18326188191","","收拾家务","有时候我们开发的app需要方便用户简单登录，可以让用户使用自己的qq、微信、微博登录到我们自己开发的app。\n" +
                "\n" +
                "今天就在这里总结一下如何在自己的app中集成QQ授权登录获取用户信息的功能。",bf.format(new Date()).toString(),"锦绣社区32号",15,"ssunhouzi",435432,false,1000,"https://gss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portrait/item/19ef64616461cfc4b1a64f0b.jpg",10);
        task.save();
        task=new Task(bf.format(new Date()).toString(),"18391","","狗屁【i","有时候我们开发的app需要方便用户简单登录，可以让用户使用自己的qq、微信、微博登录到我们自己开发的app。\n" +
                "\n" +
                "今。",bf.format(new Date()).toString(),"锦绣社区32号",15,"ssunhouzi",435432,false,1000,"https://gss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portrait/item/19ef64616461cfc4b1a64f0b.jpg",10);


    }
}
