package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStation extends AppCompatActivity {

    DatabaseHelperS myDB;
    EditText name, type, number;
    Button submit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
        myDB = new DatabaseHelperS(this);
        name = findViewById((R.id.stationLocation));
        type = findViewById((R.id.stattype));
        number = findViewById((R.id.statno));
        submit4 = findViewById(R.id.submit4);
        Submit();

    }
    public void  Submit()
    {
        submit4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(number.getText().toString()) || TextUtils.isEmpty(type.getText().toString())) {
                            Toast.makeText(AddStation.this, "Enter all Details", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean isInserted =  myDB.insertData(name.getText().toString(), type.getText().toString(), number.getText().toString() );
                        if (isInserted)
                            Toast.makeText(AddStation.this, "Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddStation.this, "Data Not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
