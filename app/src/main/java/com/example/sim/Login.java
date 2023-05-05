package com.example.sim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextView register;
    CheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        // Login Button
        Button start = (Button) findViewById(R.id.login);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(Login.this, Home.class));
            }
        });

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });

        rememberMe = (CheckBox) findViewById(R.id.checkBox);
        rememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rememberMe.isChecked()){
                    Toast.makeText(Login.this, "Login details saved successfully", Toast.LENGTH_SHORT).show();
                    rememberMe.setTextColor(getResources().getColor(R.color.app_theme));
                }
                else{
                    Toast.makeText(Login.this, "Login details not saved", Toast.LENGTH_SHORT).show();
                    rememberMe.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }
}