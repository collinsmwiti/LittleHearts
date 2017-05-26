// package of the main activity
package com.example.collins.littlehearts;

//imports
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//class main activity extending app compat activity
public class MainActivity extends AppCompatActivity {
    private Button madoptPetsButton;
    private EditText mLocationEditText;
    private TextView mAppNameTextView;

    // an override method to enhance behaviour of the items to take place in main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        madoptPetsButton = (Button) findViewById(R.id.adoptPetsButton);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface ranchoFont = Typeface.createFromAsset(getAssets(), "fonts/Rancho.ttf");
        mAppNameTextView.setTypeface(ranchoFont);
        madoptPetsButton.setOnClickListener(new View.OnClickListener() {

            // an override method to enhance action to take place when you move to the next activity
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, PetsActivity.class );
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
