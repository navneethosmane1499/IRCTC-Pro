package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class BTBook extends AppCompatActivity {

    Bundle extrasBundle;
    DatabaseHelper db;
    String trainNo, source, destination;
    ArrayList<Integer> seats;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btbook);
        setTitle("Enter Personal Details");
        db = new DatabaseHelper(this);
        extrasBundle = getIntent().getExtras();
        trainNo = extrasBundle.getString("trainNo");
        source = extrasBundle.getString("source");
        destination = extrasBundle.getString("destination");
        seats = extrasBundle.getIntegerArrayList("seats");
        textView = findViewById(R.id.fare);
        String message = "₹" + (db.getJourneyTime(new String[] {trainNo, source, destination})*seats.size()/2);
        textView.setText(message);
    }

    public void confirmTicket(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("PNR", (byte[]) null);
        contentValues.put("SOURCE", source);
        contentValues.put("DESTINATION", destination);
        contentValues.put("TRAIN_NO", trainNo);
        contentValues.put("DATE", extrasBundle.getString("date"));
        textView = findViewById(R.id.enterName);
        if(TextUtils.isEmpty(textView.getText().toString())) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        contentValues.put("NAME", textView.getText().toString());
        textView = findViewById(R.id.enterPhone);
        if(textView.getText().toString().length() != 10) {
            Toast.makeText(this, "Enter valid Phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        contentValues.put("CONTACT", textView.getText().toString());
        String pnr = db.addReservation(contentValues);
        if(pnr.equals("Oops")) {
            Toast.makeText(this, "Error 1", Toast.LENGTH_SHORT).show();
            return;
        }
        String compartmentNo = extrasBundle.getString("compartmentNo");
        for (Integer i : seats) {
            contentValues = new ContentValues();
            contentValues.put("PNR", pnr);
            contentValues.put("SEAT_NO", trainNo + compartmentNo + i.toString());
            if(db.addReservedSeats(contentValues)) {
                Toast.makeText(this, "Error 2", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String oldSeatingString = extrasBundle.getString("seatingString");
        char[] newSeatingString = new char[oldSeatingString.length()];
        for(int i=0; i<oldSeatingString.length(); i++) {
            if(seats.contains(i+1))
                newSeatingString[i] = 'b';
            else
                newSeatingString[i] = oldSeatingString.charAt(i);
        }
        db.addSeatingString(trainNo, compartmentNo, new String(newSeatingString));
        Intent intent = new Intent(this, BTThanks.class);
        intent.putExtra("pnr", pnr);
        startActivity(intent);
    }
}