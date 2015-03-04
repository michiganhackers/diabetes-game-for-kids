package com.michiganhackers.gamediabetesparentandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread timer = new Thread() {
            public void run() {
                try {
                    //sleep 5 seconds
                    sleep(5000);

                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);

                    //end splash screen - is it bad that this will end after everything else is done?
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}

