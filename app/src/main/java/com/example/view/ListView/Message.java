package com.example.view.ListView;

public class Message{
    private String content; //消息内容
    private boolean sended; //是否发送
    public Message(){}

    public Message(String content,boolean sended){
        this.content = content;
        this.sended = sended;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public boolean isSended(){
        return sended;
    }

    public void setSended(){
        this.sended = sended;
    }

}