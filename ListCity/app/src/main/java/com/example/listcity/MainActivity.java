package com.example.listcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView cityListView;
    private ArrayAdapter<String> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities={"Edmonton","Vancouver,","Seattle"};
        List<String> cityList= new ArrayList<>(Arrays.asList(cities));
        cityAdapter= new ArrayAdapter<>(this,R.layout.content,cityList);

        cityListView=findViewById(R.id.city_list_view);
        cityListView.setAdapter(cityAdapter);
    }
}
