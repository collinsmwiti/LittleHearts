//package
package com.example.collins.littlehearts.ui;

//imports

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.util.OnPetSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

//class PetsActivity extending appcompat activity
public class PetsActivity extends AppCompatActivity implements OnPetSelectedListener {
    private Integer mPosition;
    ArrayList<Pet> mPets;
    String mSource;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;
////    public static final String TAG = PetsActivity.class.getSimpleName();
//
////    @Bind(R.id.locationTextView) TextView mLocationTextView;
////    @Bind(R.id.listView) ListView mListView;
//    @Bind(R.id.recyclerView)RecyclerView mRecyclerView;
//
//    private PetListAdapter mAdapter;
//
//    public ArrayList<Pet> mPets = new ArrayList<>();

    // used to show in text format the pets to adopt near the user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mPets = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_PETS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mPets != null) {
                    Intent intent = new Intent(this, PetDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_PETS, Parcels.wrap(mPets));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }

            }

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mPets != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_PETS, Parcels.wrap(mPets));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }


    //overriding onpetselectedlistener interface
    @Override
    public void onPetSelected(Integer position, ArrayList<Pet> pets, String source) {
        mPosition = position;
        mPets = pets;
        mSource = source;

    }
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
////        mLocationTextView.setText("Here are all the pets to adopt near: " + location);
//        getPets(location);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//////        Log.d("Shared Pref Location", mRecentAddress);
//        if (mRecentAddress != null) {
//            getPets(mRecentAddress);
//        }
//    }
//
//    private void getPets(String location) {
//        final PetFinderService petFinderService = new PetFinderService();
//
//        petFinderService.adoptPets(location, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//             mPets = petFinderService.processResults(response);
//
//                PetsActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter = new PetListAdapter(getApplicationContext(), mPets);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PetsActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
////                        String[] petBreeds = new String[mPets.size()];
////                        for (int i = 0; i < petBreeds.length; i++) {
////                            petBreeds[i] = mPets.get(i).getBreed();
////                        }
////                        ArrayAdapter adapter = new ArrayAdapter(PetsActivity.this, android.R.layout.simple_list_item_1, petBreeds);
////                        mListView.setAdapter(adapter);
////                        for (Pet pet : mPets) {
////                            Log.d(TAG, "Image url: " + pet.getImageUrl());
////                            Log.d(TAG, "Id: " + pet.getId());
////                            Log.d(TAG, "Breed: " + pet.getBreed());
////                            Log.d(TAG, "Sex: " + pet.getSex());
////                            Log.d(TAG, "Description: " + pet.getDescription());
////                            Log.d(TAG, "Mix: " + pet.getMix());
////                            Log.d(TAG, "Animal: " + pet.getAnimal());
////                            Log.d(TAG, "Age: " + pet.getAge());
////                            Log.d(TAG, "Size: " + pet.getSize());
////                            Log.d(TAG, "Options: " + pet.getOptions());
////                            Log.d(TAG, "Last update: " + pet.getLastUpdate());
////                            Log.d(TAG, "Phone: " + pet.getPhone());
////                            Log.d(TAG, "Address: " + pet.getAddress());
////                        }
//                    }
//                });
//            }
//        });
//    }
//
//    //retrieving data from sharedpreferences
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
//
//    //stashing data from shared preferences
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        //adding a searchview listener
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getPets(query);
//                return  false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return  true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

}
