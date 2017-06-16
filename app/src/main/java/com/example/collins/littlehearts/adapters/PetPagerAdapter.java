package com.example.collins.littlehearts.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.ui.PetDetailFragment;

import java.util.ArrayList;

/**
 * Created by collins on 6/6/17.
 */

public class PetPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Pet> mPets;
    private String mSource;

    public PetPagerAdapter(FragmentManager fm, ArrayList<Pet> pets, String source) {
        super(fm);
        mPets = pets;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return PetDetailFragment.newInstance(mPets, position, mSource);
    }

    @Override
    public int getCount() {
        return mPets.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPets.get(position).getBreeds().get(0);
    }
}