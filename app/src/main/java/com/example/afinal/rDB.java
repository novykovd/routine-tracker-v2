package com.example.afinal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {rEntity.class}, version = 1)
public abstract class rDB extends RoomDatabase {

    public abstract mDao Dao();
    private static volatile rDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static rDB getDB(final Context context){
        if(INSTANCE == null){
            synchronized (rDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), rDB.class, "routine_DB")
                            .allowMainThreadQueries().addCallback(RDC).build();
                }
            }
        }
        return INSTANCE; //change later cuz idk multithreading OR NOT
    }

    private static RoomDatabase.Callback RDC = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                mDao Dao = INSTANCE.Dao();
                rEntity e1 = new rEntity("First");
                rEntity e2 = new rEntity("Second");
                rEntity e3 = new rEntity("Third");
                Dao.insert(e1);
                Dao.insert(e2);
                Dao.insert(e3);
            });

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            mDao Dao = INSTANCE.Dao();
            int c = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

            int rW = 0;
            LiveData<List<rEntity>> myStuff = Dao.show("rName");
            try{
                rW = Objects.requireNonNull(Dao.show("rName").getValue()).get(0).rW;
                if ( c != rW) {
                    //copy all rEntitys with new week
                    Objects.requireNonNull(Dao.show("rName").getValue()).forEach(rEntity -> {
                        rEntity r = new rEntity(rEntity.rN, rEntity.rD, rEntity.rI, rEntity.rG, rEntity.rC, rEntity.rT);
                        Dao.insert(r);
                    });
                }
            }
            catch (Exception ignored){}


        }
    };
}
