package com.example.collins.littlehearts.util;

import com.example.collins.littlehearts.models.Pet;

import java.util.ArrayList;

/**
 * Created by collins on 6/16/17.
 */

//interface onpetselectedlistener
public interface OnPetSelectedListener {
    public void onPetSelected(Integer position, ArrayList<Pet> pets);
}
