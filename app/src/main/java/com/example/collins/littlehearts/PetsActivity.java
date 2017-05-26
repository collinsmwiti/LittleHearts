//package
package com.example.collins.littlehearts;

//imports
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//class PetsActivity extending appcompat activity
public class PetsActivity extends AppCompatActivity {
    private TextView mLocationTextView;

    // used to show in text format the animal shelters near the user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the animal shelters near:" + location);
        
    }

}
