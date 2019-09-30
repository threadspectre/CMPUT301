//I created this class to keep track of the total distance without having to keep track of lots of variables and pass arguments between AddRide fragment and MainActivity
package com.example.ridebook;

public class TotalCounter {
    private Double total;
    //Previous distance is used for comparison when editing rides in order to properly update the total
    private Double previousDistance;

    public TotalCounter() {
        this.total=0.0;
        this.previousDistance=0.0;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getPreviousDistance() {
        return previousDistance;
    }

    public void setPreviousDistance(Double previousDistance) {
        this.previousDistance = previousDistance;
    }
}
