package com.example.ridebook;

import java.util.Date;

public class Ride {
    private String date;
    private String time;
    private double distance;
    private double averagespeed;
    private int rpm;
    private String comment;

    public Ride(String date, String time, double distance, double averagespeed, int rpm, String comment) {
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

    public void setDate(String date) {
        if(date!=null) {
            this.date = date;
        }
        else{
            System.out.println("You must enter a date");
        }
    }

    public void setTime(String time) {
        if(time!=null) {
            this.time = time;
        }
        else{
            System.out.println("You must enter a time");
        }
    }


    public void setDistance(double distance) {
        if(distance>0){
            this.distance = distance;
        }
        else{
            System.out.println("Distance cannot be 0");
        }
    }

    public void setAveragespeed(double averagespeed) {
        this.averagespeed = averagespeed;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }

    public double getAveragespeed() {
        return averagespeed;
    }

    public int getRpm() {
        return rpm;
    }

    public String getComment() {
        return comment;
    }
}
