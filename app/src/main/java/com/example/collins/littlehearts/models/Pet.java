//package
package com.example.collins.littlehearts.models;

//imports

import java.util.ArrayList;

/**
 * Created by collins on 6/2/17.
 */

//class pet
public class Pet {
    ArrayList<String> mImageUrls;
    String mId;
    ArrayList<String> mBreeds;
    String mSex;
    String mDescription;
    String mMix;
    String mAnimal;
    String mAge;
    String mSize;
    ArrayList<String> mOptions;
    String lastUpdate;
    String mPhone;
    String mAddress;

    //constructor pet
    public Pet(ArrayList<String> imageUrls, String id, ArrayList<String> breeds, String sex, String description, String mix, String animal, String age, String size, ArrayList<String> options, String lastUpdate, String phone, String address) {
        this.mImageUrls = imageUrls;
        this.mId = id;
        this.mBreeds = breeds;
        this.mSex = sex;
        this.mDescription = description;
        this.mMix = mix;
        this.mAnimal = animal;
        this.mAge = age;
        this.mSize = size;
        this.mOptions = options;
        this.lastUpdate = lastUpdate;
        this.mPhone = phone;
        this.mAddress = address;
    }

        //getter methods

    public ArrayList<String> getImageUrls() {
        return mImageUrls;
    }

    public String getId() {
        return mId;
    }

    public ArrayList<String> getBreeds() {
        return mBreeds;
    }

    public String getSex() {
        return mSex;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getMix() {
        return mMix;
    }

    public String getAnimal() {
        return mAnimal;
    }

    public String getAge() {
        return mAge;
    }

    public String getSize() {
        return mSize;
    }

    public ArrayList<String> getOptions() {
        return mOptions;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getAddress() {
        return mAddress;
    }
}



