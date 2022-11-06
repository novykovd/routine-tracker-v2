package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "routine_DB")
public class rEntity {
    //entity for a routine
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int getId() {
        return id;
    }

    @ColumnInfo(name = "rName")
    @NonNull
    public String rN;

    public rEntity(@NonNull String rN){ this.rN = rN; }

    @ColumnInfo(name = "rDesc")
    public String rD;

    @ColumnInfo(name = "rGoal")
    public int rND;

    @ColumnInfo(name = "rImportance")
    public int rI;

    @ColumnInfo(name = "rTime taken")
    public int rT;

    @NonNull
    public String getrN(){
        return this.rN;
    }

    //add other attributes for future

}
