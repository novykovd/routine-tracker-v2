package com.example.afinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface mDao {
    //db access object

    @Insert
    void insert(rEntity r);

    @Query("SELECT * FROM routine_DB ORDER BY rName ASC")
    LiveData<List<rEntity>> show();

    @Delete
    void delete(rEntity r);

    @Update
    void update(rEntity r);

    // ADD MORE & MORE COMPLEX
}
