package com.example.irctcpro;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class view_station extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_station);

        DatabaseHelperS mybdT = new DatabaseHelperS(this);
        Cursor res = mybdT.get_station();

        String[] Station_no = new String[res.getCount()];
        String[] Station_names = new String[res.getCount()];
        String[] Station_type  = new String[res.getCount()];

        int i = 0;
        while (res.moveToNext()) {
            Station_no[i] = res.getString(0);
            Station_names[i] = res.getString(1);
            Station_type[i++] = res.getString(2);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter_station(Station_names, Station_no, Station_type));

    }
}
