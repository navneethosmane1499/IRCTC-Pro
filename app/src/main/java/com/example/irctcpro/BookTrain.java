package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class BookTrain extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_train);
    }

    public void getTravelDate (View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void searchTrains (View view) {
        EditText editText = findViewById(R.id.enterDate);
        if(editText.getText().toString().compareTo(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Calendar.getInstance().getTime())) < 0) {
            Toast.makeText(this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle extrasBundle = new Bundle();
        extrasBundle.putString("date", editText.getText().toString());
        editText = findViewById(R.id.enterSource);
        extrasBundle.putString("source", editText.getText().toString());
        editText = findViewById(R.id.enterDestination);
        extrasBundle.putString("destination", editText.getText().toString());
        Intent intent = new Intent(this, BTSelectTrain.class);
        intent.putExtras(extrasBundle);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        EditText button = findViewById(R.id.enterDate);
        button.setText(date);
    }
}