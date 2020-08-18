package com.example.irctcpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class station_options extends AppCompatActivity {

    Button add_station, del_station, view_station;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_options);

        add_station = findViewById(R.id.add_station);
        del_station = findViewById(R.id.delete_station);
        view_station = findViewById(R.id.view_stations);

        add_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(station_options.this, AddStation.class);
                startActivity(intent);
            }
        });

        del_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(station_options.this, DelStation.class);
                startActivity(intent);
            }
        });

        view_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(station_options.this, view_station.class);
                startActivity(intent);
            }
        });
    }
}
