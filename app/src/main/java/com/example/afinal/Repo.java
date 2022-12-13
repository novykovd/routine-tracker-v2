package com.example.afinal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;

public class Repo {
    private mDao Dao;
    private LiveData<List<rEntity>> obj;
    private LiveData<List<rEntity>> obj2;

    Repo(Context context){
        int w = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        rDB db = rDB.getDB(context);
        Dao = db.Dao();

        obj2 = Dao.showByWeek(w);
    }


    LiveData<List<rEntity>> show(String parameter) {
        obj = Dao.show(parameter);
        return obj;
    }

    LiveData<List<rEntity>> showByWeek() { return obj2; }

    void add(rEntity E) { rDB.databaseWriteExecutor.execute(() ->
    { Dao.insert(E); });}

    void delete(rEntity E) { rDB.databaseWriteExecutor.execute(() ->
    { Dao.delete(E); });}

    void update(rEntity E) { rDB.databaseWriteExecutor.execute(() ->
    { Dao.update(E); });}

    int whichWeek() { return Dao.whichWeek(); }
}
