package com.example.irctcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelperA extends SQLiteOpenHelper {
    public static String TABLE_NAME = "ARRIVES_AT";
    private static  final String COL_1 = "TRAIN_NO";
    private static  final String COL_2 = "STATION_NO";
    private static  final String COL_3= "TIME";

    public DatabaseHelperA(@Nullable Context context) {
        super(context, "DBMS.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean insertData(String trainNo, String StationNo,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA FOREIGN_KEYS = ON");
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_1, trainNo);
        contentvalues.put(COL_2,StationNo );
        contentvalues.put(COL_3,time );
        return (db.insert(TABLE_NAME,null, contentvalues) != -1);
    }
    public boolean updateData(String trainNo, String stationNo,String time ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA FOREIGN_KEYS = ON");
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_3, time);
        return db.update(TABLE_NAME, contentvalues, "TRAIN_NO = ? AND STATION_NO = ?;", new String[] {trainNo, stationNo}) != 0;
    }

}