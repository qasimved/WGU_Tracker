package com.motoomaster.wgutracker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(SplashScreen.this);
                if(sharedPreferencesManager.getStringVar("name").equals("")){
                    startActivity(new Intent(SplashScreen.this, StudentRegistertion.class));
                }
                else{
                    startActivity(new Intent(SplashScreen.this,Home.class));
                }
                finish();

            }
        }, 5000);
    }
}
