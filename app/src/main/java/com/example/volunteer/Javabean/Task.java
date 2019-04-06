package com.example.volunteer.Javabean;


import cn.bmob.v3.BmobObject;

/**
 * Created by 29208 on 2019/4/5.
 */

public class Task  extends BmobObject {
    private String startTime;
    private String publishTime;
    private String place;
    private int value;
    private String publishMan;
    private int checkCode;
    private boolean isover;
    private int needMan;
    private String icon;
    private int nowMan;
    private String content;
    private String title;
    private String picture;
    private String phone;
    public Task(){}
    public  Task(String startTime,String phone,String picture,String title,String content,String publishTime,String place,
                 int value,String publishMan,int checkCode,boolean isover,
                 int needMan,String icon,int nowMan){
        this.startTime=startTime;
        this.phone=phone;
        this.picture=picture;
        this.content=content;
        this.publishTime=publishTime;
        this.place=place;
        this.value=value;
        this.publishMan=publishMan;
        this.checkCode=checkCode;
        this.isover=isover;
        this.needMan=needMan;
        this.nowMan=nowMan;
        this.icon=icon;
        this.title=title;
    }

    public String getPicture() {
        return picture;
    }

    public String getPhone() {
        return phone;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getIcon() {
        return icon;
    }

    public int getNeedMan() {
        return needMan;
    }

    public int getNowMan() {
        return nowMan;
    }

    public int getValue() {
        return value;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public int getCheckCode() {
        return checkCode;
    }

    public String getPlace() {
        return place;
    }

    public String getPublishMan() {
        return publishMan;
    }



    public Boolean getIsOver(){
        return isover;
    }

    public void setCheckCode(int checkCode) {
        this.checkCode = checkCode;
    }

    public void setIsover(boolean isover) {
        this.isover = isover;
    }

    public void setNeedMan(int needMan) {
        this.needMan = needMan;
    }

    public void setNowMan(int nowMan) {
        this.nowMan = nowMan;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPublishTime(String time) {
        this.publishTime = time;
    }

    public void setPublishMan(String publishMan) {
        this.publishMan = publishMan;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
