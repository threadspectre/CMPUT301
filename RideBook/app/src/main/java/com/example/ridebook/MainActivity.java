package com.example.ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddRideFragment.OnFragmentInteractionListener {
    private ListView rideListView;
    private ArrayAdapter<Ride> rideAdapter;
    private Button addRideButton;
    private Button deleteRideButton;
    public Ride toDelete;
    public ArrayList<Ride> RideList = new ArrayList<Ride>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rideListView = findViewById(R.id.ride_list_view);
        deleteRideButton=findViewById(R.id.deleteRideButton);
        addRideButton=findViewById(R.id.addRideButton);
        addRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddRideFragment().show(getSupportFragmentManager(), "ADD_RIDE");

            }
        });
        rideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddRideFragment.newInstance(RideList.get(position)).show(getSupportFragmentManager(), "EDIT_RIDE");
            }
        });

        rideListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rideListView.setSelection(position);
                view.setSelected(true);
                setToDelete(RideList.get(position));
                deleteListener listener= new deleteListener(getToDelete(),rideAdapter);
                deleteRideButton.setOnClickListener(listener);
                return true;
            }

        });

        rideAdapter = new RideAdapter(this, RideList);

        rideListView.setAdapter(rideAdapter);

    }

    //Both are interface methods from AddRideFragment to allow for addition and editing of rides.
    public void addRide(Ride ride){
        rideAdapter.add(ride);
        rideAdapter.notifyDataSetChanged();
    }
    public void editRide(Ride ride,String date,String time,Double distance,Double averageSpeed,int rpm,String comment){
        ride.setTime(time);
        ride.setRpm(rpm);
        ride.setDate(date);
        ride.setDistance(distance);
        ride.setComment(comment);
        ride.setAveragespeed(averageSpeed);
        rideAdapter.notifyDataSetChanged();
    }

    public void setToDelete(Ride toDelete) {
        this.toDelete = toDelete;
    }

    public Ride getToDelete() {
        return toDelete;
    }
}
//}
