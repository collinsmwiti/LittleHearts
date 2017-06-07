//package
package com.example.collins.littlehearts.models;

//imports

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by collins on 6/2/17.
 */

//class pet
@Parcel
public class Pet {
    String mImageUrl;
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
    String mEmail;


//    empty constructor for parcelar and firebase

    public Pet() {
    }

    //constructor pet
    public Pet(String imageUrl, String id, ArrayList<String> breeds, String sex, String description, String mix, String animal, String age, String size, ArrayList<String> options, String lastUpdate, String phone, String email) {
        mImageUrl = imageUrl;
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
        this.mEmail = email;

    }

    //getter methods

    public String getImageUrl() {
        return mImageUrl;
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

    public String getEmail() {
        return mEmail;
    }

}




