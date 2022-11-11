package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "routine_DB")
public class rEntity {
    //entity for a routine
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "rName")
    @NonNull
    public String rN;

    public rEntity(@NonNull String rN){ this.rN = rN; }

//    @Ignore
//    public rEntity(String rN, String rD, int rI, int rND){
//        this.rD = rD;
//        this.rN = rN;
//        this.rI = rI;
//        this.rND = rND;
//    }

    @ColumnInfo(name = "rDesc")
    public String rD;

    @ColumnInfo(name = "rGoal")
    public int rND;

    @ColumnInfo(name = "rImportance")
    public int rI;

    @ColumnInfo(name = "rTime taken")
    public int rT;


    //add other attributes for future

}
