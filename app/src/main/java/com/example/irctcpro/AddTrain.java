package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrain extends AppCompatActivity {

    DatabaseHelperT db;
    EditText number, name, type;
    Button submit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);
        db = new DatabaseHelperT(this);
        number = findViewById((R.id.trainno));
        name = findViewById((R.id.trname));
        type = findViewById((R.id.Type));
        submit2 = findViewById(R.id.submit2);
        Submit();
    }
    public void  Submit()
    {
        submit2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(number.getText().toString()) || TextUtils.isEmpty(type.getText().toString())) {
                            Toast.makeText(AddTrain.this, "Enter all Details", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean isInserted =  db.insertData(number.getText().toString(), name.getText().toString(), type.getText().toString());
                        if(isInserted)
                            Toast.makeText(AddTrain.this, "Train Added to Database",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddTrain.this, "Train Already Present in Database",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}