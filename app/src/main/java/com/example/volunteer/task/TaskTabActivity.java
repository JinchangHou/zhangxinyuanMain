package com.example.volunteer.task;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.volunteer.R;

public class TaskTabActivity extends TabActivity {
    public TaskTabActivity activity;
    public static TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_tab);
        Resources res =getResources();
        mTabHost=this.getTabHost();
        activity=this;

        Intent intent=new Intent();
        intent.setClass(this,GetTaskActivity.class);
        TabHost.TabSpec tabSpec=mTabHost.newTabSpec("领取任务").setIndicator("领取任务",res.getDrawable(R.drawable.zhihui)).setContent(intent);
        mTabHost.addTab(tabSpec);

        intent=new Intent();
        intent.setClass(this,PublishTaskActivity.class);
        intent.putExtra("name","value");
        tabSpec=mTabHost.newTabSpec("发布任务").setIndicator("发布任务",res.getDrawable(R.drawable.zan)).setContent(intent);
        mTabHost.addTab(tabSpec);


        intent = new Intent();
        intent.setClass(this, ViewTaskActivity.class);
        tabSpec = TaskTabActivity.mTabHost.newTabSpec("查看任务").
                setIndicator("查看任务", res.getDrawable(android.R.drawable.ic_media_next)).setContent(intent);
        TaskTabActivity.mTabHost.addTab(tabSpec);
        mTabHost.setCurrentTabByTag("领取任务");
    }
}
