package com.example.ridebook;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public int timee;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        populateSetTime(hour, minute);
    }
    public void populateSetTime(int hour, int minute) {
        timee=hour+minute;
    }

}
