package com.example.irctcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(@Nullable Context context) {
        super(context, "DBMS.db", null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TRAINS (TRAIN_NO TEXT PRIMARY KEY, TRAIN_NAME TEXT, TRAIN_TYPE TEXT );");
        db.execSQL("CREATE TABLE STATIONS (STATION_NO TEXT PRIMARY KEY, STATION_LOCATION TEXT, STATION_TYPE TEXT);");
        db.execSQL("CREATE TABLE DELAY (TRAIN_NO TEXT PRIMARY KEY, DELAY TEXT, FOREIGN KEY (TRAIN_NO) REFERENCES TRAINS(TRAIN_NO) ON DELETE CASCADE);");
        db.execSQL("CREATE TABLE ARRIVES_AT (TRAIN_NO TEXT, STATION_NO TEXT, TIME TEXT, PRIMARY KEY (TRAIN_NO, STATION_NO), FOREIGN KEY (TRAIN_NO) REFERENCES TRAINS (TRAIN_NO) ON DELETE CASCADE, FOREIGN KEY (STATION_NO) REFERENCES STATIONS (STATION_NO) ON DELETE CASCADE);");
        db.execSQL("CREATE TABLE RESERVATIONS (PNR INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CONTACT TEXT, SOURCE TEXT, DESTINATION TEXT, DATE TEXT, TRAIN_NO TEXT, FOREIGN KEY (SOURCE) REFERENCES STATIONS (STATION_NO) ON DELETE SET NULL, FOREIGN KEY (DESTINATION) REFERENCES STATIONS (STATION_NO) ON DELETE SET NULL);");
        db.execSQL("CREATE TABLE RESERVED_SEATS (PNR INTEGER, SEAT_NO TEXT PRIMARY KEY, FOREIGN KEY (PNR) REFERENCES RESERVATIONS (PNR) ON DELETE CASCADE);");
        db.execSQL("CREATE TABLE SEATS (TRAIN_NO TEXT PRIMARY KEY, COACH1 TEXT, COACH2 TEXT, COACH3 TEXT, COACH4 TEXT, COACH5 TEXT, COACH6 TEXT, COACH7 TEXT, COACH8 TEXT, COACH9 TEXT, COACH10 TEXT, FOREIGN KEY (TRAIN_NO) REFERENCES TRAINS (TRAIN_NO) ON DELETE CASCADE);");

        db.execSQL("CREATE TRIGGER ADD_SEATS AFTER INSERT ON TRAINS BEGIN INSERT INTO SEATS VALUES (NEW.TRAIN_NO, 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu', 'uuuuuuuuuuuuuuuuuuuuuuuuuuuu'); END");

        db.execSQL("INSERT INTO TRAINS VALUES ('100', 'Mangalore Mysore Express', 'Express'), ('101', 'Mangalore Mysore Express', 'Express'), ('200', 'Tippu Express', 'Express'), ('201', 'Tippu Express', 'Express'), ('300', 'MYS SBC Passenger', 'Passenger'), ('301', 'MYS SBC Passenger', 'Passenger');");
        db.execSQL("INSERT INTO STATIONS VALUES ('MYS01', 'Mysore', 'Junction'), ('MLR01', 'Mangalore', 'Central'), ('BLR01', 'Bangalore', 'Junction'), ('BLR02', 'Bangalore', 'Central');");
        db.execSQL("INSERT INTO ARRIVES_AT VALUES ('100', 'MYS01', '0700'), ('100', 'MLR01', '1400'), ('101', 'MYS01', '1400'), ('101', 'MLR01', '0500'), ('200', 'MYS01', '1400'), ('200', 'BLR02','1700'), ('201', 'MYS01', '1700'), ('201', 'BLR02','1400'), ('300', 'MYS01', '1400'), ('300', 'BLR01', '1800'), ('301', 'MYS01', '1800'), ('301', 'BLR01', '1400');");
        db.execSQL("INSERT INTO RESERVATIONS VALUES (256, 'You found an Easter Egg', '123', 'MYS01', 'MLR01', '24/12/19','100'), (null, 'Some random dude', '1234', 'MYS01', 'MLR01', '24/12/19','100');");
        db.execSQL("INSERT INTO RESERVED_SEATS VALUES (256, '100COACH11'), (256, '100COACH12'), (257, '100COACH110');");
        db.execSQL("UPDATE SEATS SET COACH1 = 'bbuuuuuuubuuuuuuuuuuuuuuuuuu' WHERE TRAIN_NO = '100'");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    Cursor getTrainForSourceAndDestination(String[] stations) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT T.TRAIN_NO, TRAIN_NAME from ARRIVES_AT X INNER JOIN TRAINS T ON X.TRAIN_NO = T.TRAIN_NO AND STATION_NO = ?" +
                " AND TIME < (SELECT TIME from ARRIVES_AT WHERE STATION_NO = ? and train_no = X.TRAIN_NO)", stations);
    }

    int getJourneyTime(String[] strings) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT TIME FROM ARRIVES_AT WHERE TRAIN_NO = ? AND STATION_NO IN (?, ?) ORDER BY TIME;", strings);
        cursor.moveToNext();
        int src = Integer.parseInt(cursor.getString(0));
        cursor.moveToNext();
        int dest = Integer.parseInt(cursor.getString(0));
        cursor.close ();
        return dest-src;
    }

    String getSeatingString(String[] strings) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + strings[0] + " FROM SEATS WHERE TRAIN_NO = '" + strings[1] + "';", null);
        cursor.moveToNext();
        String seatingString = cursor.getString(0);
        cursor.close ();
        return seatingString;
    }

    String addReservation(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        if (db.insert("RESERVATIONS",null, contentValues) == -1)
            return "Oops";
        Cursor cursor = db.rawQuery("SELECT PNR FROM RESERVATIONS", null);
        cursor.moveToLast();
        String pnr = cursor.getString(0);
        cursor.close ();
        return pnr;
    }

    boolean addReservedSeats(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("RESERVED_SEATS",null, contentValues) == -1;
    }

    void addSeatingString(String trainNo, String compartmentNo, String seatingString) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SEATS SET " + compartmentNo + " = '" + seatingString + "' WHERE TRAIN_NO = '" + trainNo + "';");
    }

    boolean pnrExists(String pnr) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT PNR FROM RESERVATIONS WHERE PNR = " + pnr, null);
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        db.close();
        return exists;
    }

    boolean trainExists(String pnr) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT T.TRAIN_NO FROM TRAINS T INNER JOIN RESERVATIONS R ON T.TRAIN_NO = R.TRAIN_NO WHERE PNR = " + pnr, null);
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        db.close();
        return exists;
    }

    int getDelay(String pnr) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DELAY FROM DELAY D INNER JOIN RESERVATIONS R ON D.TRAIN_NO = R.TRAIN_NO WHERE PNR = " + pnr, null);
        int delay = 0;
        if(cursor.moveToNext())
            delay = Integer.parseInt(cursor.getString(0));
        cursor.close();
        db.close();
        return delay;
    }

    void getPnrDetails(String pnr, TextView textView, int delay) {
        SQLiteDatabase db = getReadableDatabase();
        String message;
        Cursor cursor1 = db.rawQuery("SELECT DATE, SOURCE, DESTINATION, R.TRAIN_NO, TRAIN_NAME, NAME FROM RESERVATIONS R INNER JOIN TRAINS T ON R.TRAIN_NO = T.TRAIN_NO WHERE PNR = " + pnr, null);
        cursor1.moveToNext();
        message = "Train Number : " + cursor1.getString(3) + "\nTrain Name : " + cursor1.getString(4) + "\nJourney Date : " + cursor1.getString(0);
        Cursor cursor2 = db.rawQuery("SELECT TIME FROM ARRIVES_AT WHERE TRAIN_NO = ? AND STATION_NO IN (?, ?) ORDER BY TIME",
                new String[] {cursor1.getString(3), cursor1.getString(1), cursor1.getString(2)});
        cursor2.moveToNext();
        message += "\nBoarding Station Code : " + cursor1.getString(1) + "\nArrival at Boarding Station : " + cursor2.getString(0) + " + 0" + delay;
        cursor2.moveToNext();
        message += "00 Hours\nAlighting Station Code : " + cursor1.getString(2) + "\nArrival at Alighting Station : " + cursor2.getString(0) + " + 0" + delay
                + "00 Hours\nBooked by : " + cursor1.getString(5);
        textView.setText(message);
        cursor1.close();
        cursor2.close();
        db.close();
    }
}