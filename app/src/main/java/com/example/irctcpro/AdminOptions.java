package com.example.irctcpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);
    }

    public void train_fun(View view){
        Intent intent = new Intent(this, train_options.class);
        startActivity(intent);
    }
    public void station_fun(View view){
        Intent intent = new Intent(this,station_options.class);
        startActivity(intent);
    }
    public void arrival_fun(View view){
        Intent intent = new Intent(this,AddArrival.class);
        startActivity(intent);

    }
    public void delay_fun(View view){
        Intent intent = new Intent(this,AddDelay.class);
        startActivity(intent);
    }
}
