package com.example.volunteer.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.volunteer.Javabean.Task;
import com.example.volunteer.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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

    private Task mTask=new Task();
    private TextView title;
    private TextView content;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_content);
        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        initView();
        requestData();
        findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                    mTask=list.get(position);
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
                }

            }
        });

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
    }

}
