package com.example.ridebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RideAdapter extends ArrayAdapter<Ride> {
    private Context context;
    private List<Ride> rides;

    public RideAdapter(Context context,List<Ride> rides) {
        super(context, 0, rides);
        this.context = context;
        this.rides = rides;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.ride_list_content,parent,false);
        }

        TextView averageSpeedTextView= view.findViewById(R.id.avg_speed_entry);
        TextView rpmTextView=view.findViewById(R.id.RPM_entry);
        TextView distanceTextView=view.findViewById(R.id.distance_entry);
        TextView dateTextView=view.findViewById(R.id.date_entry);
        TextView timeTextView=view.findViewById(R.id.time_entry);
        TextView commentTextView=view.findViewById(R.id.comment_entry);

        Ride ride=rides.get(position);

        averageSpeedTextView.setText(Double.toString(ride.getAverageSpeed()));
        rpmTextView.setText(Integer.toString(ride.getRpm()));
        distanceTextView.setText(Double.toString(ride.getDistance()));
        dateTextView.setText(ride.getDate());
        timeTextView.setText(ride.getTime());
        commentTextView.setText(ride.getComment());

        return view;
    }

}