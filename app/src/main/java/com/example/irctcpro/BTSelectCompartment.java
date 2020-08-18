package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BTSelectCompartment extends AppCompatActivity {

    Bundle extrasBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btselect_compartment);
        setTitle("Select Compartment");
        extrasBundle = getIntent().getExtras();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterCompartment(new String[] {"COACH1", "COACH2", "COACH3", "COACH4", "COACH5", "COACH6", "COACH7", "COACH8", "COACH9", "COACH10"}));
    }
    public void goToSelectSeats(View view) {
        TextView textView = view.findViewById(R.id.compartment_no);
        extrasBundle.putString("compartmentNo", textView.getHint().toString());
        Intent intent = new Intent(this, BTSelectSeats.class);
        intent.putExtras(extrasBundle);
        startActivity(intent);
    }
}