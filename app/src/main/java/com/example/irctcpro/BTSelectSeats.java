package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class BTSelectSeats extends AppCompatActivity {

    ArrayList<Integer> seats = new ArrayList<>();
    String seatingString;
    Bundle extrasBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btselect_seats);
        setTitle("Select Seats");
        extrasBundle = getIntent().getExtras();
        DatabaseHelper db = new DatabaseHelper(this);
        seatingString = db.getSeatingString(new String[] {extrasBundle.getString("compartmentNo"), extrasBundle.getString("trainNo")});
        StringBuilder[] str = {new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder()};
        for(int i = 0; i< seatingString.length(); i++)
            str[i%4].append(seatingString.charAt(i));
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterSeats(str[0].toString(),1));
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterSeats(str[1].toString(),2));
        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterSeats(str[2].toString(),3));
        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterSeats(str[3].toString(),4));
    }

    public void addSeat (View view) {
        Button button = view.findViewById(R.id.seat);
        int seatNo = Integer.parseInt(button.getText().toString());
        if(seatingString.charAt(seatNo-1) == 'b')
            Toast.makeText(this, "Seat already Booked", Toast.LENGTH_SHORT).show();
        else {
            if(seats.remove(Integer.valueOf(seatNo))) {
                button.setBackgroundResource(R.drawable.green_seat);
                Toast.makeText(this, "Unselected", Toast.LENGTH_SHORT).show();
            }
            else {
                button.setBackgroundResource(R.drawable.blue_seat);
                seats.add(seatNo);
            }
        }
    }

    public void book(View view) {
        if(seats.size() == 0) {
            Toast.makeText(this, "Select Seat(s)", Toast.LENGTH_SHORT).show();
            return;
        }
        extrasBundle.putIntegerArrayList("seats",seats);
        extrasBundle.putString("seatingString", seatingString);
        Intent intent = new Intent(this, BTBook.class);
        intent.putExtras(extrasBundle);
        startActivity(intent);
    }
}