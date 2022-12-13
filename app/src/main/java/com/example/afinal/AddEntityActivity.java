package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEntityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CustomViewModelJ customViewModel = new ViewModelProvider(this).get(CustomViewModelJ.class);
        final MutableLiveData<String> liveDataCategory = new MutableLiveData<>();
        final MutableLiveData<Float> liveDataTime = new MutableLiveData<>();
        rEntity newEntity = new rEntity();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = findViewById(R.id.rC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                liveDataCategory.setValue(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                liveDataTime.setValue(Float.parseFloat(parent.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TextView tv = findViewById(R.id.textViewTime);
        //set on click listener to start time picker dialog
        tv.setOnClickListener( view -> {

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            tv.setText(hourOfDay + ":" + minute);
                            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });


        Spinner spinner2 = findViewById(R.id.rT);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_array2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(itemSelectedListener2);

        EditText name = findViewById(R.id.rN);
        EditText desc = findViewById(R.id.rD);
        EditText goal = findViewById(R.id.rG);
        EditText impr = findViewById(R.id.rI);

        Button cancelBtn = findViewById(R.id.button);
        cancelBtn.setOnClickListener(v -> {
            finish();
        });

        Button okBtn = findViewById(R.id.buttonOK);
        okBtn.setOnClickListener(v -> {
            if (!name.getText().toString().isEmpty()
                    || !desc.getText().toString().isEmpty()
                    || !goal.getText().toString().isEmpty()
                    || !impr.getText().toString().isEmpty()
                    || !liveDataCategory.getValue().isEmpty()
                    || !liveDataTime.getValue().toString().isEmpty()) {

                newEntity.rN = name.getText().toString();
                newEntity.rD = desc.getText().toString();
                newEntity.rG =Integer.parseInt(goal.getText().toString());
                newEntity.rI = Integer.parseInt(impr.getText().toString());
                newEntity.rC = liveDataCategory.getValue();
                if (tv.getText().toString().isEmpty()) { newEntity.rA = "false"; }
                else {

                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY , alarmIntent);
                    newEntity.rA = tv.getText().toString(); };
                customViewModel.insert(newEntity);
                finish();
            }
        });

    }
}

//class CustomViewModelFactory(val application :Application) : ViewModelProvider.Factory{
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return CustomViewModel(application) as T
//        }
//        }

//class CustomViewModelFactory(Application app) implements ViewModelProvider.Factory {
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass, Application app) {
//        return (T) new CustomViewModel(app);
//    }
//}