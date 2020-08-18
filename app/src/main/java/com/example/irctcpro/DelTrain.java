package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DelTrain extends AppCompatActivity {

    DatabaseHelperT MyDB = new DatabaseHelperT(this);
    Button delete;
    EditText train_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_train);

        train_no = findViewById(R.id.editText);
        delete = findViewById(R.id.del_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete_data();
            }
        });
    }

    public void Delete_data(){
        String train_number = train_no.getText().toString();
        Integer deletedrows = MyDB.delete_data(train_number);
        if(deletedrows > 0){
            Toast.makeText(this, "Train Deleted", Toast.LENGTH_LONG).show();}
        else{
            Toast.makeText(this, "Train not Found", Toast.LENGTH_LONG).show();
        }
    }
}
