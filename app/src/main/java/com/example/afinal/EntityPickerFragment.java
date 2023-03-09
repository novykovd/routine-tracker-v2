package com.example.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class EntityPickerFragment extends Fragment {
    NewEntityViewModel vm = new ViewModelProvider(this).get(NewEntityViewModel.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (vm.radioButtonChoice)
        { //3 cases for 3 radio buttons
            case "Project":
                //set conentview to fragment_routine_add

                break;
            case "Routine":

                break;
            case "Task":
                //set fragment to fragment3
                break;
        }
    }
}