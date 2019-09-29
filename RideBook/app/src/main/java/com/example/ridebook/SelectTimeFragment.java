package com.example.ridebook;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SelectTimeFragment extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "TimePickerFragment";
    final Calendar c = Calendar.getInstance();

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Set the current time as the default time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        // Return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(), SelectTimeFragment.this, hour,minute,false);
    }

    // called when a time has been selected
    public void onTimeSet(TimePicker view, int hour,int minute) {
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        String selectedTime = new SimpleDateFormat("HH:MM", Locale.ENGLISH).format(c.getTime());

        Log.d(TAG, "onTimeSet: " + selectedTime);
        // send time back to the target fragment
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedTime", selectedTime)
        );
    }
}
