package com.example.irctcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelperT extends SQLiteOpenHelper {

    public static String TABLE_NAME = "TRAINS";

    DatabaseHelperT(@Nullable Context context) {
        super(context, "DBMS.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean insertData(String TRAIN_NO, String TRAIN_NAME, String TYPE ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("TRAIN_NO", TRAIN_NO);
        contentvalues.put("TRAIN_NAME", TRAIN_NAME);
        contentvalues.put("TRAIN_TYPE", TYPE);
        return db.insert(TABLE_NAME, null, contentvalues) != -1;
    }


    String[] getTrainNameType(String train_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("CREATE TEMP VIEW TRAIN_NAME_TYPE AS SELECT TRAIN_NAME, TRAIN_TYPE FROM TRAINS WHERE TRAIN_NO = '" + train_no + "';");
        Cursor cursor = db.rawQuery("SELECT * FROM TRAIN_NAME_TYPE", null);
        String[] result = {"x", "x"};
        if(cursor.moveToNext()) {
            result[0] = cursor.getString(0);
            result[1] = cursor.getString(1);
        }
        cursor.close();
        db.close();
        return result;
    }

    Cursor getDestination(String train_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT STATION_LOCATION, STATION_NO FROM STATIONS WHERE STATION_NO IN (SELECT STATION_NO FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + " AND TIME = ( SELECT MAX(TIME) FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + " AND STATION_NO IN (SELECT STATION_NO FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + ")))", null);
    }

    Cursor getSource(String train_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT STATION_LOCATION, STATION_NO FROM STATIONS WHERE STATION_NO IN (SELECT STATION_NO FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + " AND TIME = ( SELECT MIN(TIME) FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + " AND STATION_NO IN (SELECT STATION_NO FROM ARRIVES_AT WHERE TRAIN_NO = " + train_no + ")))", null);
    }

    Integer delete_data(String num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA FOREIGN_KEYS = ON");
        return db.delete(TABLE_NAME, "TRAIN_NO = ?", new String[] {num});
    }

    Cursor get_train(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}