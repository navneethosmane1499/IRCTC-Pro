package com.example.irctcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void login(View view){
        EditText admin_name = findViewById(R.id.name);
        String name = admin_name.getText().toString();
        EditText admin_pass = findViewById(R.id.password);
        String pass = admin_pass.getText().toString();
        if(name.equals("admin") && pass.equals("admin")) {
            Intent intent = new Intent(this, AdminOptions.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show();
    }
}