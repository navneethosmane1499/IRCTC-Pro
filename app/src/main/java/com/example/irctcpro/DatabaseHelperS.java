package com.example.irctcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperS extends SQLiteOpenHelper {

    public static String TABLE_NAME = "STATIONS";
    private static  final String COL_1 = "STATION_NO";
    private static  final String COL_2 = "STATION_LOCATION";
    private static  final String COL_3 = "STATION_TYPE";

    DatabaseHelperS(@Nullable Context context) {
        super(context, "DBMS.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean insertData(String StationLocation, String StationType, String StationNumber ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_1 ,StationNumber);
        contentvalues.put(COL_2 ,StationLocation);
        contentvalues.put(COL_3 ,StationType);
        return (db.insert(TABLE_NAME,null,contentvalues) != -1);
    }

    public Integer delete_data(String num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA FOREIGN_KEYS = ON");
        return db.delete(TABLE_NAME, "STATION_NO = ?", new String[] {num});
    }

    public Cursor get_station(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
