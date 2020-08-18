package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddArrival extends AppCompatActivity {
    EditText editText, editText1, editText2;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_arrival);
        editText = findViewById(R.id.textviewtrainno);
        editText1 = findViewById(R.id.textviewstatno);
        editText2 = findViewById(R.id.timeTime);
        submit = findViewById(R.id.submit1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArrival();
            }
        });
    }

    public void addArrival() {
        DatabaseHelperA databaseHelper = new DatabaseHelperA(this);
        String trainNo = editText.getText().toString();
        String stationNo = editText1.getText().toString();
        String time = editText2.getText().toString();
        if(TextUtils.isEmpty(trainNo) || TextUtils.isEmpty(stationNo) || TextUtils.isEmpty(time)) {
            Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!databaseHelper.insertData(trainNo, stationNo, time )) {
            if (databaseHelper.updateData(trainNo,stationNo,time))
                Toast.makeText(this, "Arrival Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Train or Station not found", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Arrival Added", Toast.LENGTH_SHORT).show();
    }
}