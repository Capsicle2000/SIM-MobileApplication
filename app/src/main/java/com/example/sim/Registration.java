package com.example.sim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        // Back Button
        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(Registration.this, Login.class));
            }
        });





        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabPersonalInfo = (TabItem) findViewById(R.id.tabPersonalInfo);
        TabItem tabAccountDetails = (TabItem) findViewById(R.id.tabAccountDetails);

        ViewPager viewPager = findViewById(R.id.viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }

}