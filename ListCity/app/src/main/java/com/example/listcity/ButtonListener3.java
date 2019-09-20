package com.example.listcity;

import android.view.View;
import android.widget.ArrayAdapter;

public class ButtonListener3 implements View.OnClickListener {
    private String toDelete;
    private ArrayAdapter<String> cityAdapter;

    public ButtonListener3(String toDelete, ArrayAdapter<String> cityAdapter) {
        this.toDelete = toDelete;
        this.cityAdapter = cityAdapter;
    }

    @Override
    public void onClick(View view) {
        if(getToDelete()!=null){
            cityAdapter.remove(getToDelete());
            setToDelete(null);
        }
    }

    public String getToDelete() {
        return toDelete;
    }

    public void setToDelete(String toDelete) {
        this.toDelete = toDelete;
    }
}
