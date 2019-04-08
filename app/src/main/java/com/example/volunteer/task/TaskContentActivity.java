package com.example.volunteer.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.volunteer.Javabean.MyTask;
import com.example.volunteer.Javabean.Task;
import com.example.volunteer.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class TaskContentActivity extends AppCompatActivity {

    ImageView icon;
    TextView publishMan;
    TextView publishTime;
    TextView startTime;
    TextView place ;
    TextView needMan;
    TextView nowMan;
    TextView phone;
    TextView value;

    public Task mTask=new Task();
    private TextView title;
    private TextView content;
    private int position;
    public static  String objectId="";
    public static int countNow=0;
    private Button get;
    public  Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        requestData();
        setContentView(R.layout.activity_task_content);
        initView();





        findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();

                finish();

            }
        });

    }

    private void updateData(){
        Log.d("查询数据",objectId);
        if(mTask.getIsOver()){
            return;
        }
        if(mTask.getNowMan()>=mTask.getNeedMan()){
            Toast.makeText(this,"报名人数已满，请选择其他任务",Toast.LENGTH_LONG).show();
            return;
        }

        mTask.setNowMan(countNow+1);
        mTask.setIsover(true);
        Log.d("查询数据内部",mTask.getNowMan()+"");
        mTask.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Intent intent=new Intent(TaskContentActivity.this,TaskTabActivity.class);
                    //                    下面进行存储已经领取的任务
                    storageTask();
                    Toast.makeText(TaskContentActivity.this,"成功领取到一个任务",Toast.LENGTH_LONG).show();
                    context.startActivity(intent);
                    finish();


                }
            }
        });
    }
    private void storageTask(){
        final BmobQuery<Task> query=new BmobQuery<Task>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if(e==null){
                    Task task=list.get(position);
                    MyTask mytask=new MyTask();
                    mytask.setCheckCode(task.getCheckCode());
                    mytask.setContent(task.getContent());
                    mytask.setIcon(task.getIcon());
                    mytask.setLeibie("帮助老人");
                    mytask.setTitle(task.getTitle());
                    mytask.setStartTime(task.getStartTime());
                    mytask.setPicture(task.getPicture());
                    mytask.setPublishMan(task.getPublishMan());
                    mytask.save();

                }
            }
        });
    }
    private void requestData(){
        final BmobQuery<Task> query=new BmobQuery<Task>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if(e==null){

                    mTask=list.get(position);countNow=mTask.getNowMan();
                    objectId=mTask.getObjectId();
                    Log.d("获得数据",mTask.getPhone()+" "+mTask.getPublishTime()+" "+mTask.getNeedMan());

                    title.setText(mTask.getTitle());
                    content.setText(mTask.getContent());


                    Glide.with(TaskContentActivity.this).load(mTask.getIcon()).into(icon);
                    publishMan.setText(mTask.getPublishMan());
                    value.setText(mTask.getValue()+"");

                    phone.setText(mTask.getPhone()+"");
                    nowMan.setText(""+mTask.getNowMan()+"");
                    needMan.setText(mTask.getNeedMan()+"");
                    place .setText(mTask.getPlace()+"");

                    startTime.setText(mTask.getStartTime()+"");
                    publishTime.setText(mTask.getPublishTime()+"");

                    if(mTask.getIsOver()){
                        get.setText("已领取");
                        Log.d("已经领取",true+"");
                        get.setBackgroundColor(getResources().getColor(R.color.font66));
                    }
                    if(mTask.getNeedMan()<=mTask.getNowMan()){
                        get.setText("人数已满");
                        get.setBackgroundColor(getResources().getColor(R.color.font66));
                    }
                }

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
        }
        return true;
    }

    private void initView(){
        icon=findViewById(R.id.icon);
       publishMan=findViewById(R.id.publish_man);
       value=findViewById(R.id.value_aixin);
       phone=findViewById(R.id.phone);
       nowMan=findViewById(R.id.nowman);
       needMan=findViewById(R.id.needman);
       place=findViewById(R.id.place);
       startTime=findViewById(R.id.start_time);
       publishTime=findViewById(R.id.publish_time);
       title=findViewById(R.id.title);
       content=findViewById(R.id.content);
       get=findViewById(R.id.get_task);
    }

}
