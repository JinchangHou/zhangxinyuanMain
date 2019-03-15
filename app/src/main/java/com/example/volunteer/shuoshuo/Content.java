package com.example.volunteer.shuoshuo;


import android.util.Log;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by 29208 on 2018/10/1.
 */
public class Content extends BmobObject implements Serializable {
    private String  imageUrl="http://bmob-cdn-22722.b0.upaiyun.com/2018/12/07/65e5124f40dece4280eebb41355eb6ae.jpg";
//    private List<String>imageUrlList=new ArrayList<>();
    private String title="\".Bmob(国产 移动后端服务) 数据存储 推送 短信 支付 即时通信 \"";
    private int countZan=0;
    private List<Comment> commitList=new ArrayList<Comment>();


    private String time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    private String name="侯金昌";

    Comment comment;
    private String myName="侯金昌";

    private String personIcon="http://www.ruanyifeng.com/blogimg/asset/2016/bg2016072208.png";
    public Content(){
        this.commitList.add(new Comment());
        this.commitList.add(new Comment());
        this.commitList.add(new Comment());
    }
    public Content(String myname, String personIcon, String imageUrl, String title,
                   int countZan, List<Comment> commitlist, String time, String name){
        this.imageUrl=imageUrl;
        this.title=title;
        this.countZan=countZan;
        this.commitList=commitlist;
        this.name=name;
        this.time=time;
        this.personIcon=personIcon;
        this.myName=myname;
        Log.d("construct",getTime());
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getPersonIcon() {
        return personIcon;
    }

    public void setPersonIcon(String personIcon) {
        this.personIcon = personIcon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountZan() {
        return countZan;
    }

    public void setCountZan(int countZan) {
        this.countZan = countZan;
    }

    public List<Comment> getCommitList() {
        return commitList;
    }

    public void setCommitList(List<Comment> commitList) {
        this.commitList = commitList;
    }
    public void appendCommitList(Comment c){
        commitList.add(c);
    }
}
