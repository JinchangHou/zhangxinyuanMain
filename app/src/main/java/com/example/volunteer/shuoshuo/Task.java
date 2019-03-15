package com.example.volunteer.shuoshuo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 29208 on 2018/12/4.
 */
public class Task extends BmobObject {
    public Task(){

    }
    public Task(Task task){
        this.time=task.time;
        this.location=task.location;
        this.peopleNeed=task.peopleNeed;
        this.peopleSign=task.peopleSign;
        this.isDone=task.isDone;
        this.loveMoney=task.loveMoney;
    }
    public Task(String location, int peopleNeed, int peopleSign, boolean isDone, int loveMoney){
        this.time=time;
        this.location=location;
        this.peopleNeed=peopleNeed;
        this.peopleSign=peopleSign;
        this.isDone=isDone;
        this.loveMoney=loveMoney;
    }
    private String time;
    private String location;
    private int peopleNeed;
    private int peopleSign;
    private boolean isDone=false;
    private int loveMoney;
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getPeopleNeed() {
        return peopleNeed;
    }
    public void setPeopleNeed(int peopleNeed) {
        this.peopleNeed = peopleNeed;
    }
    public int getPeopleSign() {
        return peopleSign;
    }
    public void setPeopleSign(int peopleSign) {
        this.peopleSign = peopleSign;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public int getLoveMoney() {
        return loveMoney;
    }
    public void setLoveMoney(int loveMoney) {
        this.loveMoney = loveMoney;
    }

    private BmobFile image;
    public BmobFile getImage(){
        return image;
    }

}
