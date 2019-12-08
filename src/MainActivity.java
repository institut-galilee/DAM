package com.example.findpt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int Splash_time=5500; //Ecran d'accueil s'affiche pour 5,5 secondes puis disparait.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home_intent=new Intent(MainActivity.this,Ecran2.class );
                startActivity(home_intent);
                finish();
            }
        },Splash_time);
    }
}
