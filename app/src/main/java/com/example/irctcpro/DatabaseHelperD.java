package com.example.irctcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelperD extends SQLiteOpenHelper {

    public static String TABLE_NAME = "DELAY";
    private static  final String COL_1 = "TRAIN_NO";
    private static  final String COL_2 = "DELAY";

    public DatabaseHelperD(@Nullable Context context) {
        super(context, "DBMS.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean insertData(String trainNo, String delay ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA FOREIGN_KEYS = ON");
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_1, trainNo);
        contentvalues.put(COL_2, delay);
        return (db.insert(TABLE_NAME,null, contentvalues) != -1);
    }

    public boolean updateData(String trainNo, String delay ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2, delay);
        return db.update(TABLE_NAME, contentvalues, COL_1 + " = " + trainNo, null) != 0;
    }
}
