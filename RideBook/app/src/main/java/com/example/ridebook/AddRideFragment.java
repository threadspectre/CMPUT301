//This code is a modified version of the Lab Tutorial on fragments
package com.example.ridebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import static android.text.TextUtils.isEmpty;

public class AddRideFragment extends DialogFragment {
    private EditText distanceEditText;
    private EditText averageSpeedEditText;
    private EditText rpmEditText;
    private EditText commentEditText;
    private EditText dateEditText;
    private EditText timeEditText;
    private OnFragmentInteractionListener listener;
    private Ride ride;
    //Sets fragment title to Add Ride by default
    protected String title="Add Ride";
    //Variable required by selectDate and selectTime fragments in order to communicate with this fragment
    //Based on: https://brandonlehr.com/android/learn-to-code/2018/08/19/callling-android-datepicker-fragment-from-a-fragment-and-getting-the-date?fbclid=IwAR0ixIB3nbIx7k2gQpu1Nz3VU48pg5ii3grksnRqgLNr-TcDZgV2QHg0uXA
    public static final int REQUEST_CODE = 11;

    //Added editRide functionality since the fragment wasn't updating the ride correctly without it
    interface OnFragmentInteractionListener{
        public void addRide(Ride ride);
        public void editRide(Ride ride,String date,String time,Double distance,Double averageSpeed,int rpm,String comment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(OnFragmentInteractionListener) context;
    }

    public static AddRideFragment newInstance(Ride ride){
        Bundle args = new Bundle();
        args.putSerializable("ride",ride);

        AddRideFragment fragment=new AddRideFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_entry,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());


        distanceEditText=view.findViewById(R.id.distance_entry_add);
        averageSpeedEditText= view.findViewById(R.id.avg_speed_entry_add);
        dateEditText= view.findViewById(R.id.date_entry_add);
        rpmEditText= view.findViewById(R.id.RPM_entry_add);
        timeEditText= view.findViewById(R.id.time_entry_add);
        commentEditText= view.findViewById(R.id.comment_entry_add);
        /*This implementation of datepicker is based on:
        https://brandonlehr.com/android/learn-to-code/2018/08/19/callling-android-datepicker-fragment-from-a-fragment-and-getting-the-date?fbclid=IwAR0ixIB3nbIx7k2gQpu1Nz3VU48pg5ii3grksnRqgLNr-TcDZgV2QHg0uXA
         */
        final FragmentManager dm = (getActivity()).getSupportFragmentManager();
        dateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new SelectDateFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddRideFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(dm, "datePicker");
            }
        });
        //Modified datepicker to provide timepicker functionality as well
        //Also based on:  https://brandonlehr.com/android/learn-to-code/2018/08/19/callling-android-datepicker-fragment-from-a-fragment-and-getting-the-date?fbclid=IwAR0ixIB3nbIx7k2gQpu1Nz3VU48pg5ii3grksnRqgLNr-TcDZgV2QHg0uXA
        final FragmentManager tm = (getActivity()).getSupportFragmentManager();
        timeEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // create the timePickerFragment
                AppCompatDialogFragment newFragment = new SelectTimeFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddRideFragment.this, REQUEST_CODE);
                // show the timePicker
                newFragment.show(tm, "timePicker");
            }
        });

        if(getArguments()!=null){
            //Gets ride object from bundle
            ride=(Ride) getArguments().getSerializable("ride");
            distanceEditText.setText(Double.toString(ride.getDistance()));
            averageSpeedEditText.setText(Double.toString(ride.getAverageSpeed()));
            dateEditText.setText(ride.getDate());
            rpmEditText.setText(Integer.toString(ride.getRpm()));
            timeEditText.setText(ride.getTime());
            commentEditText.setText(ride.getComment());
            //Just a small detail, but changes fragment to title to Edit Ride if the ride is not null
            if(ride!=null){
                title="Edit Ride";
            }
        }

        return builder.setView(view)
                .setTitle(title)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Double distance;
                        Double avgSpeed;
                        Integer rpm;
                        String date;
                        String time;
                        String comment;
                        //Prevent empty values from being entered by defaulting them
                        if(isEmpty(distanceEditText.getText())){
                            distanceEditText.setText("0.0");
                        }
                        if(isEmpty(dateEditText.getText())){
                            dateEditText.setText("2000-01-01");
                        }
                        if(isEmpty(timeEditText.getText())){
                            timeEditText.setText("00:00");
                        }
                        if(isEmpty(rpmEditText.getText())){
                            rpmEditText.setText("0");
                        }
                        if(isEmpty(commentEditText.getText())){
                            commentEditText.setText("");
                        }
                        if(isEmpty(averageSpeedEditText.getText())){
                            averageSpeedEditText.setText("0.0");
                        }
                        distance= Double.parseDouble(distanceEditText.getText().toString());
                        avgSpeed= Double.parseDouble(averageSpeedEditText.getText().toString());
                        rpm= Integer.parseInt(rpmEditText.getText().toString());
                        date= dateEditText.getText().toString();
                        time= timeEditText.getText().toString();
                        comment= commentEditText.getText().toString();
                        //Prevents negative numbers, though it should be impossible to type them in as the keyboard will not register the negative sign
                        if(distance<0.0){
                            distance*=-1;
                        }
                        if(avgSpeed<0.0){
                            distance*=-1;
                        }
                        if(rpm<0){
                            rpm*=-1;
                        }

                        //TODO: Fix bug where empty inputs in add ride crash the app
                        //EDIT: Fixed above by defaulting values where input is empty
                        if(ride==null){

                            Ride ride= new Ride(date,time,distance,avgSpeed,rpm,comment);
                            listener.addRide(ride);
                        }
                        else {
                            listener.editRide(ride,date,time,distance,avgSpeed,rpm,comment);
                        }

                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
    }

    //Slightly modified version of datepicker, also obtained from:
    //https://brandonlehr.com/android/learn-to-code/2018/08/19/callling-android-datepicker-fragment-from-a-fragment-and-getting-the-date?fbclid=IwAR0ixIB3nbIx7k2gQpu1Nz3VU48pg5ii3grksnRqgLNr-TcDZgV2QHg0uXA
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            String selectedDate = data.getStringExtra("selectedDate");
            String selectedTime = data.getStringExtra("selectedTime");
            // set the value of the editText
            if(selectedTime!=null){
                timeEditText.setText(selectedTime);
            }
            if(selectedDate!=null){
                dateEditText.setText(selectedDate);
            }
        }
    }

}
