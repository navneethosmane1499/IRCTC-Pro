package com.example.irctcpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //And also a FireBase Auth object
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //first we initialised the FireBaseAuth object
        mAuth = FirebaseAuth.getInstance();
        new DatabaseHelper(this);
    }

    public void bookTrain (View view) {
        Intent intent = new Intent(this, BookTrain.class);
        startActivity(intent);
    }

    public void adminLogin (View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }

    public void PNREnquiry (View view) {
        Intent intent = new Intent(this, PNREnquiry.class);
        startActivity(intent);
    }

    public void trainInfo(View view){
        Intent intent = new Intent(this, TrainInfo.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            case R.id.sign_out:
                // FireBase sign out
                mAuth.signOut();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Boolean exit = false;
    public void onBackPressed() {
        if (exit) {
            moveTaskToBack(true); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}