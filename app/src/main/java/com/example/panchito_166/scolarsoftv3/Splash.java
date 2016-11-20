package com.example.panchito_166.scolarsoftv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    private long espera = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask (){

            @Override
            public void run() {
                Intent llamar;
                llamar = new Intent(Splash.this,login.class);
                startActivity(llamar);
                Splash.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,espera);


    }
}
