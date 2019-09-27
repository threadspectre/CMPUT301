package com.example.listcity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRideListener implements View.OnClickListener {
    private EditText nameEditText;
    private Button nameSubmit;

    public AddRideListener(EditText nameEditText, Button nameSubmit) {
        this.nameEditText = nameEditText;
        this.nameSubmit = nameSubmit;
    }

    @Override
    public void onClick(View view) {
        nameEditText.setVisibility(View.VISIBLE);
        nameSubmit.setVisibility(View.VISIBLE);
    }
}
