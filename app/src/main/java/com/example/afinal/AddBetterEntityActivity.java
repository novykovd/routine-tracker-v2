package com.example.afinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// create android activity that would set entitypickerfragment class as conent in fragment container of layout_add_new via fragment manager
public class AddBetterEntityActivity extends AppCompatActivity {

    NewEntityViewModel vm = new ViewModelProvider(this).get(NewEntityViewModel.class);
    CustomViewModel vm2 = new ViewModelProvider(this, new CustomViewModelFactory(getApplication())).get(CustomViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_new);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).add(R.id.fragmentContainerView, EntityPickerFragment.class, null).commit();
        }

        //spinner for categories
        Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        //button for canceling
        Button btn2 = findViewById(R.id.button);
        btn2.setOnClickListener(v -> {
            finish();
        });

        //button for adding new entity
        Button btn = findViewById(R.id.buttonOK);
        btn.setOnClickListener(v -> {
            //start Addition activity
            rEntity entity = vm.entity.getValue();
            vm2.insert(entity);
            finish();
        });






    }
}


