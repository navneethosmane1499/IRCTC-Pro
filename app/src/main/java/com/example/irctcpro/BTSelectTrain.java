package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BTSelectTrain extends AppCompatActivity {

    Bundle extrasBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btselect_trains);
        setTitle("Select Train");

        extrasBundle = getIntent().getExtras();
        assert extrasBundle != null;
        String[] stations = {extrasBundle.getString("source"), extrasBundle.getString("destination")};
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getTrainForSourceAndDestination(stations);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Sorry, no Trains Available", Toast.LENGTH_LONG).show();
        }
        String[] trainNos = new String[cursor.getCount()];
        String[] trainNames = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            trainNos[i] = cursor.getString(0);
            trainNames[i++] = cursor.getString(1);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterTrains(trainNos, trainNames));
    }

    public void goToSelectCompartment(View view) {
        TextView textView = view.findViewById(R.id.textView);
        extrasBundle.putString("trainNo", textView.getHint().toString());
        Intent intent = new Intent(this, BTSelectCompartment.class);
        intent.putExtras(extrasBundle);
        startActivity(intent);
    }
}