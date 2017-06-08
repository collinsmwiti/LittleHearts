// package of the main activity
package com.example.collins.littlehearts.ui;

//imports

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

//class main activity extending app compat activity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;


    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.adoptPetsButton) Button mAdoptPetsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

//    private Button madoptPetsButton;
//    private EditText mLocationEditText;
//    private TextView mAppNameTextView;

    // an override method to enhance behaviour of the items to take place in main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);


//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

//        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
//        madoptPetsButton = (Button) findViewById(R.id.adoptPetsButton);
//        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface ranchoFont = Typeface.createFromAsset(getAssets(), "fonts/Rancho.ttf");
        mAppNameTextView.setTypeface(ranchoFont);
        mAdoptPetsButton.setOnClickListener(this);
    }

            // an override method to enhance action to take place when you move to the next activity
            @Override
            public void onClick(View v) {
                if (v == mAdoptPetsButton) {
                    String location = mLocationEditText.getText().toString();

                    saveLocationToFirebase(location);
//                    if (!(location).equals("")) {
//                        addToSharedPreferences(location);
//                }
                    Intent intent = new Intent(MainActivity.this, PetsActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
    }

