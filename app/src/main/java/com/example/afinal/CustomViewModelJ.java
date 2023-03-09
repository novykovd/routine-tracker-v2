package com.example.afinal;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CustomViewModelJ extends AndroidViewModel {
    private final Repo MyRepo;
    private final LiveData<List<rEntity>> entityList;

    public CustomViewModelJ(Application app){
        super(app);
        MyRepo = new Repo(app);
        entityList = MyRepo.show();
    }

    LiveData<List<rEntity>> show(){ return  entityList; }
    public void insert(rEntity entity){ MyRepo.add(entity); }

}
