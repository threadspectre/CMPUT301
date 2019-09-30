package com.example.ridebook;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//deleteRideButton listener, as the name implies it basically deletes a ride from the view.
//Based on lab tutorial on listeners
public class DeleteListener implements View.OnClickListener {
    private Ride toDelete;
    private ArrayAdapter<Ride> rideAdapter;
    private TotalCounter totalCounter;
    private TextView totalCounterTextView;

    public DeleteListener(Ride toDelete, ArrayAdapter<Ride> rideAdapter,TotalCounter totalCounter,TextView totalCounterTextView) {
        this.toDelete = toDelete;
        this.rideAdapter = rideAdapter;
        this.totalCounter=totalCounter;
        this.totalCounterTextView=totalCounterTextView;
    }

    @Override
    public void onClick(View view) {
        if(getToDelete()!=null){
            Double total=totalCounter.getTotal();
            total-=toDelete.getDistance();
            totalCounter.setTotal(total);
            rideAdapter.remove(getToDelete());
            rideAdapter.notifyDataSetChanged();
            //Show and hide button based on: //Show and hide button based on:https://stackoverflow.com/questions/21899825/show-hide-button-when-focus-the-list-item-in-android-listview
            Button deleteRideButton = (Button)view.findViewById(R.id.deleteRideButton);
            String t=Double.toString(total);
            totalCounterTextView.setText(t);
            deleteRideButton.setVisibility(View.INVISIBLE);
            setToDelete(null);
        }
    }
    //Gets ride to delete
    public Ride getToDelete() {
        return toDelete;
    }
    //Sets ride to delete
    public void setToDelete(Ride toDelete) {
        this.toDelete = toDelete;
    }
}