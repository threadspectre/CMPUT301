package com.example.ridebook;

import java.sql.Time;
import java.util.Date;

public class Ride {
    private Date date;
    private Time time;
    private float distance;
    private float averagespeed;
    private int rpm;
    private String comment;

    public Ride(Date date, Time time, float distance, float averagespeed, int rpm, String comment) {
        this.setDate(date);
        this.setTime(time);
        this.setDistance(distance);
        this.averagespeed = averagespeed;
        this.rpm = rpm;
        this.setComment(comment);
    }

    public void setComment(String comment) {
        if(comment.length() <= 20){
            this.comment = comment;
        }
        else{
            System.out.println("Comment is too long");
        }

    }

    public void setDate(Date date) {
        if(date!=null) {
            this.date = date;
        }
        else{
            System.out.println("You must enter a date");
        }
    }

    public void setTime(Time time) {
        if(time!=null) {
            this.time = time;
        }
        else{
            System.out.println("You must enter a time");
        }
    }


    public void setDistance(float distance) {
        if(distance>0){
            this.distance = distance;
        }
        else{
            System.out.println("Distance cannot be 0");
        }
    }

    public void setAveragespeed(float averagespeed) {
        this.averagespeed = averagespeed;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public float getDistance() {
        return distance;
    }

    public float getAveragespeed() {
        return averagespeed;
    }

    public int getRpm() {
        return rpm;
    }

    public String getComment() {
        return comment;
    }
}
