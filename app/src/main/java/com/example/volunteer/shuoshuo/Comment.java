package com.example.volunteer.shuoshuo;

public class Comment {
 
    String name=" "; //评论者
    String content=" "; //评论内容
 
    public Comment(){
         
    }
 
    public Comment(String name, String content){
        this.name = name;
        this.content = content;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
}