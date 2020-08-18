package com.example.irctcpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class train_options extends AppCompatActivity {

    Button add_train, del_train,view_train;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_options);

        add_train = findViewById(R.id.add_train);
        del_train = findViewById(R.id.delete_train);
        view_train = findViewById(R.id.view_trains);

        add_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(train_options.this, AddTrain.class);
                startActivity(intent);
            }
        });

        del_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(train_options.this, DelTrain.class);
                startActivity(intent);
            }
        });

        view_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(train_options.this, view_trains.class);
                startActivity(intent);
            }
        });

    }
}
