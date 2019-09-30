//Main Activity, allows us to add, edit, and delete rides and display the total distance.
package com.example.ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddRideFragment.OnFragmentInteractionListener {
    private ListView rideListView;
    private ArrayAdapter<Ride> rideAdapter;
    private Button addRideButton;
    private Button deleteRideButton;
    private TextView totalTextView;
    private TotalCounter totalCounter=new TotalCounter();
    public Ride toDelete;
    public ArrayList<Ride> RideList = new ArrayList<Ride>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rideListView = findViewById(R.id.ride_list_view);
        deleteRideButton=findViewById(R.id.deleteRideButton);
        totalTextView=findViewById(R.id.total_counter);
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
                //Gets the selected ride's distance to compare it to the edited distance later
                Ride currentRide=RideList.get(position);
                totalCounter.setPreviousDistance(currentRide.getDistance());
                //Calls AddRide fragment
                AddRideFragment.newInstance(RideList.get(position)).show(getSupportFragmentManager(), "EDIT_RIDE");
            }
        });

        rideListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rideListView.setSelection(position);
                //Show and hide button based on:https://stackoverflow.com/questions/21899825/show-hide-button-when-focus-the-list-item-in-android-listview
                deleteRideButton.setVisibility(View.VISIBLE);
                view.setSelected(true);
                setToDelete(RideList.get(position));
                //Calls the delete listener when clicking the delete ride button
                DeleteListener listener= new DeleteListener(getToDelete(),rideAdapter,totalCounter,totalTextView);
                deleteRideButton.setOnClickListener(listener);
                return true;
            }

        });
        totalTextView.setText("0.0");
        rideAdapter = new RideAdapter(this, RideList);

        rideListView.setAdapter(rideAdapter);

    }

    //Both are interface methods from AddRideFragment to allow for addition and editing of rides.
    public void addRide(Ride ride){
        //Increment total distance when adding a ride
        Double total=totalCounter.getTotal();
        total+=ride.getDistance();
        totalCounter.setTotal(total);
        totalTextView.setText(Double.toString(totalCounter.getTotal()));
        rideAdapter.add(ride);
        rideAdapter.notifyDataSetChanged();
    }
    public void editRide(Ride ride,String date,String time,Double distance,Double averageSpeed,int rpm,String comment){
        Double total=totalCounter.getTotal();
        Double previousDistance= totalCounter.getPreviousDistance();
        Double currenDistance=distance;
        //Comparison step in order to make sure that just because we selected a ride, we don't necessarily update the total unless we changed its parameters and submitted them. Allows us to cancel editing without updating the total.
        if(previousDistance!=currenDistance){
            total-=previousDistance;
            total+=currenDistance;
            totalCounter.setTotal(total);
            totalTextView.setText(Double.toString(totalCounter.getTotal()));
        }
        ride.setTime(time);
        ride.setRpm(rpm);
        ride.setDate(date);
        ride.setDistance(distance);
        ride.setComment(comment);
        ride.setAverageSpeed(averageSpeed);
        rideAdapter.notifyDataSetChanged();
    }
    //Sets ride to delete
    public void setToDelete(Ride toDelete) {
        this.toDelete = toDelete;
    }
    //Retrieves ride to delete
    public Ride getToDelete() {
        return toDelete;
    }
}
//}
