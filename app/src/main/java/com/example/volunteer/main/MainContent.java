package com.example.volunteer.main;

import cn.bmob.v3.BmobObject;

/**
 * Created by 29208 on 2019/3/19.
 */
public class MainContent extends BmobObject {
    private String imageUrl;
    private String title;
    private String text;
    public String getImageUrl(){
        return imageUrl;
    }
    public String getTitle(){
        return title;
    }
    public String getText(){
        return text;
    }
}
