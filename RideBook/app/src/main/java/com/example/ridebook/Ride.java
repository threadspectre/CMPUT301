package com.example.ridebook;

import java.io.Serializable;
//Ride Class
/*Some of the code appears redundant since I implemented default values for the parameters.
However this occurred much later on in the development process and I left this class unchanged for the most part
since I was afraid it would break my app somewhere down the line.
 */
public class Ride implements Serializable {
    private String date;
    private String time;
    private double distance;
    private double averageSpeed;
    private int rpm;
    private String comment;

    public Ride(String date, String time, double distance, double averageSpeed, int rpm, String comment) {
        this.setDate(date);
        this.setTime(time);
        this.setDistance(distance);
        this.averageSpeed = averageSpeed;
        //I defined cadence as rpm since it was more intuitive and easier to keep track of
        this.rpm = rpm;
        this.setComment(comment);
    }

    public void setComment(String comment) {
        this.comment=comment;

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

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
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

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public int getRpm() {
        return rpm;
    }

    public String getComment() {
        return comment;
    }
}
