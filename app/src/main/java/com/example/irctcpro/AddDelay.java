package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddDelay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delay);
    }

    public void addDelay(View view) {
        DatabaseHelperD databaseHelper = new DatabaseHelperD(this);
        EditText editText = findViewById(R.id.trainNoForDelay);
        String trainNo = editText.getText().toString();
        editText = findViewById(R.id.delay);
        String delay = editText.getText().toString();
        if(TextUtils.isEmpty(trainNo) || TextUtils.isEmpty(delay)) {
            Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!databaseHelper.insertData(trainNo, delay)) {
            if (databaseHelper.updateData(trainNo, delay))
                Toast.makeText(this, "Delay Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Train not found", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Delay Added", Toast.LENGTH_SHORT).show();
    }
}