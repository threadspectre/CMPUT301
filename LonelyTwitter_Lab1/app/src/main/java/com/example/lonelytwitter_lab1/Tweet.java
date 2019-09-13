package com.example.lonelytwitter_lab1;

import java.util.Date;

public abstract class Tweet implements TweetTable {
    private String text;
    private Date date;

    public Tweet(String text){
        this.setText(text);
        this.date=new Date();
    }

    public Tweet(String text, Date date) {
        this.setText(text);
        this.date = date;
    }

    public void setDate(Date date){
        this.date=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text.length() <= 140){
            this.text = text;
        }
        else{
            System.out.println("Tweet is too long");
        }

    }

    public Date getDate() {
        return date;
    }

    public abstract Boolean isImportant();

}
