package com.example.simpleparadox.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    private EditText cityEditText;
    private EditText provinceEditText;
    private OnFragmentInteractionListener listener;
    private City city;

    interface OnFragmentInteractionListener{
        public void addCity(City city);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(OnFragmentInteractionListener) context;
    }


    public static AddCityFragment newInstance(City city){
        Bundle args = new Bundle();
        args.putSerializable("city",city);

        AddCityFragment fragment=new AddCityFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());


        cityEditText=view.findViewById(R.id.city_edit_text);
        provinceEditText= view.findViewById(R.id.province_edit_text);
        if(getArguments()!=null){
            city=(City) getArguments().getSerializable("city");
            cityEditText.setText(city.getName());
            provinceEditText.setText(city.getProvince());
        }

        return builder.setView(view)
                .setTitle("Add city/Edit City")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String name= cityEditText.getText().toString();
                        String province= provinceEditText.getText().toString();

                        if(city==null){
                            City city= new City(name,province);

                            listener.addCity(city);
                        }
                        else {
                            city.setName(name);
                            city.setProvince(province);
                        }

                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
    }

}
