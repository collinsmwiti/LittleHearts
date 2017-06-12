package com.example.collins.littlehearts.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.adapters.PetPagerAdapter;
import com.example.collins.littlehearts.models.Pet;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PetDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private PetPagerAdapter adapterViewPager;
    ArrayList<Pet> mPets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        mPets = Parcels.unwrap(getIntent().getParcelableExtra("pets"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PetPagerAdapter(getSupportFragmentManager(), mPets);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}