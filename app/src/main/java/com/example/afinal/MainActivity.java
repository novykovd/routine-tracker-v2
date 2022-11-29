package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageButton btn = findViewById(R.id.imageButton);
        btn.setOnClickListener(v -> {
            //start Addition activity
            Intent intent = new Intent(this, AddEntityActivity.class);
            startActivity(intent);

        });



    }
}