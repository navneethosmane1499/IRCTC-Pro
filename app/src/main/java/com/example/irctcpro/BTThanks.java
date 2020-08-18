package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BTThanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btthanks);
        TextView textView = findViewById(R.id.thanks);
        String message = "Ticket(s) reserved Successfully\nYour PNR number is " + getIntent().getStringExtra("pnr") + "\nPlease make payment at nearest Station to confirm your booking\nThank You!";
        textView.setText(message);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
