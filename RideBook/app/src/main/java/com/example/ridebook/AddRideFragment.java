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

public class AddRideFragment extends DialogFragment {
    private EditText distanceEditText;
    private EditText averageSpeedEditText;
    private EditText rpmEditText;
    private EditText commentEditText;
    private EditText dateEditText;
    private EditText timeEditText;
    private OnFragmentInteractionListener listener;
    private Ride ride;

    public static final int REQUEST_CODE = 11;


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
        final FragmentManager fm = (getActivity()).getSupportFragmentManager();
        dateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new SelectDateFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddRideFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });

        if(getArguments()!=null){
            ride=(Ride) getArguments().getSerializable("ride");
            distanceEditText.setText(Double.toString(ride.getDistance()));
            averageSpeedEditText.setText(Double.toString(ride.getAveragespeed()));
            dateEditText.setText(ride.getDate());
            rpmEditText.setText(Integer.toString(ride.getRpm()));
            timeEditText.setText(ride.getTime());
            commentEditText.setText(ride.getComment());
        }

        return builder.setView(view)
                .setTitle("Add ride/Edit ride")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(distanceEditText.getText().toString().equals(null)){
                            distanceEditText.setText(0);
                        }
                        if(dateEditText.getText().toString().equals(null)){
                            dateEditText.setText("01/01/2000");
                        }
                        if(timeEditText.getText().toString().equals(null)){
                            timeEditText.setText("00:00");
                        }
                        if(rpmEditText.getText().toString().equals(null)){
                            rpmEditText.setText(0);
                        }
                        if(commentEditText.getText().toString().equals(null)){
                            commentEditText.setText("");//LOL talk about redundant
                        }
                        if(averageSpeedEditText.getText().toString().equals(null)){
                            averageSpeedEditText.setText(0);
                        }
                        Double distance= Double.parseDouble(distanceEditText.getText().toString());
                        Double avgSpeed= Double.parseDouble(averageSpeedEditText.getText().toString());
                        Integer rpm= Integer.parseInt(rpmEditText.getText().toString());
                        String date= dateEditText.getText().toString();
                        String time= timeEditText.getText().toString();
                        String comment= commentEditText.getText().toString();

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

    //
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            String selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            dateEditText.setText(selectedDate);
        }
    }

}
