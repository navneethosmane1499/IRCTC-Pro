package com.example.irctcpro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DelStation extends AppCompatActivity {

    DatabaseHelperS MyDB = new DatabaseHelperS(this);
    Button delete;
    EditText station_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_station);

        station_no = findViewById(R.id.get_station_no);
        delete = findViewById(R.id.button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_station();
            }
        });
    }

    public void delete_station(){
        String station = station_no.getText().toString();
        Integer deletedRows = MyDB.delete_data(station);
        if(deletedRows > 0){
            Toast.makeText(this, "Station Deleted", Toast.LENGTH_LONG).show();}
        else{
            Toast.makeText(this, "Station not Found", Toast.LENGTH_LONG).show();
        }
    }
}
