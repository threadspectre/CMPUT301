package com.example.listcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView cityListView;
    private ArrayAdapter<String> cityAdapter;
    private EditText nameEditText;
    private Button nameAddCity;
    private Button nameRemoveCity;
    private Button nameSubmit;
    private String toDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities={"Edmonton","Vancouver","Seattle"};
        List<String> cityList= new ArrayList<>(Arrays.asList(cities));
        cityAdapter= new ArrayAdapter<>(this,R.layout.content,cityList);

        cityListView=findViewById(R.id.city_list_view);
        cityListView.setAdapter(cityAdapter);

        nameEditText= findViewById(R.id.editText);
        nameAddCity= findViewById(R.id.addCityButton);
        nameRemoveCity=findViewById(R.id.removeCityButton);
        nameSubmit=findViewById(R.id.submitButton);
        //Submit button listener
        ButtonListener listener= new ButtonListener(nameEditText,cityAdapter,nameSubmit);
        //Add City Listener
        ButtonListener2 listener2= new ButtonListener2(nameEditText,nameSubmit);
        nameSubmit.setOnClickListener(listener);
        nameAddCity.setOnClickListener(listener2);
        /*This is the deletion part, essentially every time you click on an entry, you initialize the
        remove city listener*/
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cityListView.setSelection(i);
                view.setSelected(true);
                setToDelete(cityListView.getItemAtPosition(i).toString());
                ButtonListener3 listener3= new ButtonListener3(getToDelete(),cityAdapter);
                nameRemoveCity.setOnClickListener(listener3);
            }
        });

    }

    public void setToDelete(String toDelete) {
        this.toDelete = toDelete;
    }

    public String getToDelete() {
        return toDelete;
    }
}
