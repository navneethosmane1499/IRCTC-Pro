package com.example.irctcpro;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TrainInfo extends AppCompatActivity {

    Button getInfo;
    EditText get_no;
    DatabaseHelperT DB = new DatabaseHelperT(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_info);
        get_no = findViewById(R.id.trainno);
        getInfo = findViewById(R.id.get_info);
        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String train_no = get_no.getText().toString().trim();
                if(TextUtils.isEmpty(train_no))
                    Toast.makeText(TrainInfo.this, "Enter Train number", Toast.LENGTH_SHORT).show();
                else
                    Fetch_info(train_no);
            }
        });
    }

    public void Fetch_info(String train_no){
        String[] res = DB.getTrainNameType(train_no);
        if(res[0].equals("x")) {
            Toast.makeText(this, "Invalid Train number", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("Train Number : ").append(train_no).append("\nTrain Name : ").append(res[0]).append("\nTrain Type : ").append(res[1]);
        Cursor res1 = DB.getSource(train_no);
        if(res1.moveToNext()){
            buffer.append("\nSource : ").append(res1.getString(0)).append(" - ").append(res1.getString(1));
            res1 = DB.getDestination(train_no);
            res1.moveToNext();
            buffer.append("\nDestination : ").append(res1.getString(0)).append(" - ").append(res1.getString(1));
        } else
            buffer.append("\nTrain currently not Servicing!");
        show_msg("Train Information", buffer.toString());
    }

    public void show_msg(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
