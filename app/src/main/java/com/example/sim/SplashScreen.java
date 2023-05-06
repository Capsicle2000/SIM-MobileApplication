package com.example.sim;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

                if (isFirstTime) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirstTime", false);
                    editor.apply();
                    startActivity(new Intent(SplashScreen.this, Intro.class));
                }else {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }
        }, SPLASH_SCREEN);





    }
}
