package com.example.simpleparadox.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomList extends ArrayAdapter<City> {
    private Context context;
    private List<City> cities;

    public CustomList(Context context, List<City> cities){
        super(context,0,cities);
        this.context=context;
        this.cities=cities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }

        TextView cityTextView =view.findViewById(R.id.city_text_view);
        TextView provinceTextView= view.findViewById(R.id.province_text_view);

        City city= cities.get(position);

        cityTextView.setText(city.getName());
        provinceTextView.setText(city.getProvince());

        return view;
    }
}
