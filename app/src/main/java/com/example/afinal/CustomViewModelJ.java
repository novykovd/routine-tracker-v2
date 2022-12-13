package com.example.afinal;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class CustomViewModelJ extends AndroidViewModel {
    private final Repo MyRepo;
    private final LiveData<List<rEntity>> entityList;

    public CustomViewModelJ(Application app){
        super(app);
        MyRepo = new Repo(app);
        entityList = MyRepo.show("rName");

        int c = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int rW = MyRepo.whichWeek();

        try{
            if ( c != rW) {
                for(int i = 0; i < rW - 1; i++){
                    Objects.requireNonNull(MyRepo.show("rName").getValue()).forEach(rEntity -> {
                        rEntity r = new rEntity(rEntity.rN, rEntity.rD, rEntity.rI, rEntity.rG, rEntity.rC, rEntity.rT);
                        MyRepo.add(r);
                    });
                }
            }
        }
        catch (Exception ignored){ }
    }

    LiveData<List<rEntity>> show(){ return  entityList; }
    public void insert(rEntity entity){ MyRepo.add(entity); }

}
