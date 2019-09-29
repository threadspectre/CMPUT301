package com.example.ridebook;

import android.view.View;
import android.widget.ArrayAdapter;

public class deleteListener implements View.OnClickListener {
    private Ride toDelete;
    private ArrayAdapter<Ride> rideAdapter;

    public deleteListener(Ride toDelete, ArrayAdapter<Ride> rideAdapter) {
        this.toDelete = toDelete;
        this.rideAdapter = rideAdapter;
    }

    @Override
    public void onClick(View view) {
        if(getToDelete()!=null){
            rideAdapter.remove(getToDelete());
            rideAdapter.notifyDataSetChanged();
            setToDelete(null);
        }
    }

    public Ride getToDelete() {
        return toDelete;
    }

    public void setToDelete(Ride toDelete) {
        this.toDelete = toDelete;
    }
}