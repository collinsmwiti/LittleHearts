// package of the main activity
package com.example.collins.littlehearts.ui;

//imports

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collins.littlehearts.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

//class main activity extending app compat activity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

//    private DatabaseReference mSearchedLocationReference;
//
//    private ValueEventListener mSearchedLocationReferenceListener;

//    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.adoptPetsButton) Button mAdoptPetsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedPetsButton) Button mSavedPetsButton;

//    private Button madoptPetsButton;
//    private EditText mLocationEditText;
//    private TextView mAppNameTextView;

    // an override method to enhance behaviour of the items to take place in main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSavedPetsButton.setOnClickListener(this);

//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION); //pinpoint location node
//
//        // remove value event listener to prevent loss of battery power in phones especially when one quits an app
//        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location:" + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //attach listener
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location: " + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred
//
//            }
//        });


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
//                    String location = mLocationEditText.getText().toString();
//
//                    saveLocationToFirebase(location);
//                    if (!(location).equals("")) {
//                        addToSharedPreferences(location);
//                }
                    Intent intent = new Intent(MainActivity.this, PetsActivity.class);
//                    intent.putExtra("location", location);
                    startActivity(intent);
                }

                if (v == mSavedPetsButton) {
                    Intent intent = new Intent(MainActivity.this, SavedPetListActivity.class);
                    startActivity(intent);
                }
            }

//    public void saveLocationToFirebase(String location) {
//        mSearchedLocationReference.push().setValue(location);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
//    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

    //overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    }

