package com.example.afinal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        CustomViewModelJ vm = new ViewModelProvider(this).get(CustomViewModelJ.class);
        CustomViewModel vm2 = new ViewModelProvider(this, new CustomViewModelFactory(getApplication())).get(CustomViewModel.class);


        ImageButton btn = findViewById(R.id.imageButton);
        btn.setOnClickListener(v -> {
            //start Addition activity
            Intent intent = new Intent(this, AddEntityActivity.class);
            startActivity(intent);

        });

        //spinner for categories
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array3, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                vm2.setCategory("r"+parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };


        // create notification channel




//        tv.setOnClickListener(v -> {
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
//                tv.setText(hourOfDay + ":" + minute);
//            }, 0, 0, true);
//            timePickerDialog.show();
//        });



    }

    private void createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "LemubitReminderChannel";
                String description = "Channel for Lemubit Reminder";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
                channel.setDescription(description);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
    }
}