package com.example.sim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Button start = findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(Intro.this, Login.class));
            }
        });


    }
}