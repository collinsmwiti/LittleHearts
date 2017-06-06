// package
package com.example.collins.littlehearts.ui;

//imports
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.collins.littlehearts.R;

/**
 * Created by collins on 5/25/17.
 */

//class splashscreen
public class SplashScreen extends Activity {

    // an override method used to display splashscreen, also to grasp any interrupted error which might take place
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //using thread(concurrent of process) to actualize the splashscreen to work
        Thread timerThread = new Thread() {
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    // an override method is used to enhance splash screen behaviour to take place
    @Override
    protected void onPause() {
        //TODO Auto-genetated method stub
        super.onPause();
        finish();
    }
}
