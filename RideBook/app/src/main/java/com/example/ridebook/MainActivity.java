package com.example.ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private ListView rideListView;
    private ArrayAdapter<Ride> rideAdapter;
    private Button AddRide;
    public Ride sampleRide;
    public Ride sampleRide2;
    public ArrayList<Ride> RideList= new ArrayList<Ride>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd GH");
        SimpleDateFormat stf= new SimpleDateFormat("H:mm:ss");
        String currentDate = sdf.format(new Date());
        String currentTIme= stf.format(new Date());
        sampleRide= new Ride(currentDate,
                currentTIme,12.0,0,0,"");
        sampleRide2= new Ride(currentDate,
                currentTIme,14.0,0,0,"");
        RideList.add(sampleRide);
        RideList.add(sampleRide2);
        rideAdapter= new ArrayAdapter<Ride>(this,R.layout.content_main,RideList);
        rideListView=findViewById(R.id.ride_list_view);
        rideListView.setAdapter(rideAdapter);}}
//
//        nameEditText= findViewById(R.id.editText);
//        nameAddCity= findViewById(R.id.addCityButton);
//        nameRemoveCity=findViewById(R.id.removeCityButton);
//        nameSubmit=findViewById(R.id.submitButton);
//        //Submit button listener
//        ButtonListener listener= new ButtonListener(nameEditText,cityAdapter,nameSubmit);
//        //Add City Listener
//        ButtonListener2 listener2= new ButtonListener2(nameEditText,nameSubmit);
//        nameSubmit.setOnClickListener(listener);
//        nameAddCity.setOnClickListener(listener2);
//        /*This is the deletion part, essentially every time you click on an entry, you initialize the
//        remove city listener*/
//        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                cityListView.setSelection(i);
//                view.setSelected(true);
//                setToDelete(cityListView.getItemAtPosition(i).toString());
//                ButtonListener3 listener3= new ButtonListener3(getToDelete(),cityAdapter);
//                nameRemoveCity.setOnClickListener(listener3);
//            }
//        });
//
//    }
//
//    public void setToDelete(String toDelete) {
//        this.toDelete = toDelete;
//    }
//
//    public String getToDelete() {
//        return toDelete;
//    }
//}
