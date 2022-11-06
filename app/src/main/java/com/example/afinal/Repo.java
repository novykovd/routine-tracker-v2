package com.example.afinal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repo {
    private mDao Dao;
    private LiveData<List<rEntity>> obj;

    Repo(Context context){
        rDB db = rDB.getDB(context);
        Dao = db.Dao();
        obj = Dao.show();
    }

    LiveData<List<rEntity>> show() { return obj; }

    void add(rEntity E) { rDB.databaseWriteExecutor.execute(() ->
    { Dao.insert(E); });}
}
