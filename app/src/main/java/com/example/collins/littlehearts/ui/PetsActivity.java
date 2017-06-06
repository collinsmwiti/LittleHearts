//package
package com.example.collins.littlehearts.ui;

//imports

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.adapters.PetListAdapter;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.services.PetFinderService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//class PetsActivity extending appcompat activity
public class PetsActivity extends AppCompatActivity {
    public static final String TAG = PetsActivity.class.getSimpleName();

//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private PetListAdapter mAdapter;

    public ArrayList<Pet> mPets = new ArrayList<>();

    // used to show in text format the pets to adopt near the user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the pets to adopt near: " + location);
        getPets(location);
    }

    private void getPets(String location) {
        final PetFinderService petFinderService = new PetFinderService();

        petFinderService.adoptPets(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             mPets = petFinderService.processResults(response);

                PetsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PetListAdapter(getApplicationContext(), mPets);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
//                        String[] petBreeds = new String[mPets.size()];
//                        for (int i = 0; i < petBreeds.length; i++) {
//                            petBreeds[i] = mPets.get(i).getBreed();
//                        }
//                        ArrayAdapter adapter = new ArrayAdapter(PetsActivity.this, android.R.layout.simple_list_item_1, petBreeds);
//                        mListView.setAdapter(adapter);
//                        for (Pet pet : mPets) {
//                            Log.d(TAG, "Image url: " + pet.getImageUrl());
//                            Log.d(TAG, "Id: " + pet.getId());
//                            Log.d(TAG, "Breed: " + pet.getBreed());
//                            Log.d(TAG, "Sex: " + pet.getSex());
//                            Log.d(TAG, "Description: " + pet.getDescription());
//                            Log.d(TAG, "Mix: " + pet.getMix());
//                            Log.d(TAG, "Animal: " + pet.getAnimal());
//                            Log.d(TAG, "Age: " + pet.getAge());
//                            Log.d(TAG, "Size: " + pet.getSize());
//                            Log.d(TAG, "Options: " + pet.getOptions());
//                            Log.d(TAG, "Last update: " + pet.getLastUpdate());
//                            Log.d(TAG, "Phone: " + pet.getPhone());
//                            Log.d(TAG, "Address: " + pet.getAddress());
//                        }
                    }
                });
            }
        });
    }
}
