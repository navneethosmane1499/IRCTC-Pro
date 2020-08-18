package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class PNREnquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnrenquiry);
    }

    @SuppressLint("SetTextI18n")
    public void getStatus(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        TextView textView = findViewById(R.id.enterPNR);
        String pnr = textView.getText().toString();
        textView = findViewById(R.id.pnrResult);
        TextView textView2 = findViewById(R.id.pnrDetails);
        if(TextUtils.isEmpty(pnr) || !dbHelper.pnrExists(pnr)) {
            textView2.setText("");
            textView.setTextColor(Color.BLACK);
            textView.setText("Invalid PNR");
            return;
        }
        if(!dbHelper.trainExists(pnr)) {
            textView2.setText("");
            textView.setTextColor(Color.RED);
            textView.setText("Train Cancelled :(");
            return;
        }
        int delay = dbHelper.getDelay(pnr);
        if(delay == 0) {
            textView.setTextColor(Color.GREEN);
            textView.setText("Train on Time");
        } else {
            textView.setTextColor(Color.BLACK);
            textView.setText("Train delayed by " + delay + " hours");
        }
        dbHelper.getPnrDetails(pnr, textView2, delay);
    }
}
