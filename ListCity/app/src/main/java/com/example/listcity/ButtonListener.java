package com.example.listcity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class ButtonListener implements View.OnClickListener {
    private EditText nameEditText;
    private ArrayAdapter<String> cityAdapter;
    private Button nameSubmit;

    public ButtonListener(EditText nameEditText, ArrayAdapter<String> cityAdapter,Button nameSubmit) {
        this.nameEditText = nameEditText;
        this.cityAdapter = cityAdapter;
        this.nameSubmit = nameSubmit;
    }

    @Override
    public void onClick(View view) {
        String name=nameEditText.getText().toString();
        cityAdapter.setNotifyOnChange(true);
        cityAdapter.add(name);
        nameEditText.setVisibility(View.INVISIBLE);
        nameSubmit.setVisibility(View.INVISIBLE);
    }
}
