package com.example.volunteer.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.volunteer.Javabean.Task;
import com.example.volunteer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublishTaskActivity extends AppCompatActivity {

    private EditText phone ;
    private EditText money;
    private EditText startTime;
    private EditText place;
    private EditText needMan;
    private EditText content;
    private EditText title;
//    private  String testString ;

    private Button publishTask;

    private Task task=new Task();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);
        init();
        initTask();
    }
    private void initTask(){

       publishTask.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               task.setPhone(phone.getText().toString());
               task.setContent(content.getText().toString());
               task.setStartTime(startTime.getText().toString());
               task.setPlace(place.getText().toString());
               task.setNeedMan(Integer.parseInt(needMan.getText().toString()));
               task.setValue(Integer.parseInt(money.getText().toString()));
               task.setTitle(title.getText().toString());

               task.setPublishMan("侯金昌");
               task.setCheckCode(137820);
               task.setIsover(false);
               task.setIcon("http://sowcar.com/t6/696/1554532090x2362277802.jpg");
               task.setNowMan(0);
               //日期
               DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//多态
               task.setPublishTime(bf.format(new Date()).toString());
               task.save();
               Intent intent=new Intent(PublishTaskActivity.this,TaskTabActivity.class);
               startActivity(intent);
                finish();
           }
       });

    }
    private void init(){
        title=findViewById(R.id.title);
        phone=findViewById(R.id.phone);
        money=findViewById(R.id.money);
        startTime=findViewById(R.id.exe_time);
        place =findViewById(R.id.place);
        needMan=findViewById(R.id.need_man);
        content=findViewById(R.id.item_content);
        publishTask=findViewById(R.id.publish_task);
    }
}
