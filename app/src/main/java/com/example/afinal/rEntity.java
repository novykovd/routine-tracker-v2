package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "routine_DB")
public class rEntity {
    //entity for a routine
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "rName")
    @NonNull
    public String rN;

    public rEntity(@NonNull String rN){ this.rN = rN; }

    @Ignore
    public rEntity(String rN, String rD, int rI, int G) {
        this.rD = rD;
        this.rN = rN;
        this.rI = rI;
        this.rG = G;

    }
    @Ignore
    public rEntity(String rN, String rD, int rI, int rG, String rC, float rT) {
        this.rD = rD;
        this.rN = rN;
        this.rI = rI;
        this.rG = rG;
        this.rC = rC;
        this.rT = rT;
    }

    @Ignore
    public rEntity(){
        this.rD = "No Description";
        this.rN = "No Name";
        this.rI = 0;
        this.rG = 0;
    }


    @ColumnInfo(name = "rDesc")
    public String rD = "No Description";

    @ColumnInfo(name = "rImportance")
    public int rI;

    @ColumnInfo(name = "rTime")
    public float rT;

    @ColumnInfo(name = "rGoal")
    public int rG;

    @ColumnInfo(name = "rCategory")
    public String rC = "No Category";

    @ColumnInfo(name = "rDays")
    public String rDA = "false,false,false,false,false,false,false";

    @ColumnInfo(name = "rWeek")
    public int rW = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

    @ColumnInfo(name = "rAlarm")
    public String rA = "false";



    //add other attributes for future

}
