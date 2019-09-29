package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    FloatingActionButton addCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCityButton=findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");

            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddCityFragment.newInstance(dataList.get(position)).show(getSupportFragmentManager(), "EDIT_CITY");
            }
        });


        String []cities ={"Edmonton", "Vancouver","toronto"};
        String [] provinces={"AB","BC","ON"};

        dataList = new ArrayList<>();

        for(int i=0;i<cities.length;i++){
            dataList.add(new City(cities[i],provinces[i]));
        }


        cityAdapter = new CustomList(this,dataList);


        cityList.setAdapter(cityAdapter);

    }

    public void addCity(City city){

        cityAdapter.add(city);
    }


}
