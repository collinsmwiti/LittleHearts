package com.example.collins.littlehearts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by collins on 5/25/17.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        //TODO Auto-genetated method stub
        super.onPause();
        finish();
    }
}
