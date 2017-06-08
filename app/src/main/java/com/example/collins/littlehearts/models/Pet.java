//package
package com.example.collins.littlehearts.models;

//imports

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by collins on 6/2/17.
 */

//class pet
    //used POJO to adhere to java rules
@Parcel
public class Pet {
    String imageUrl;
    String id;
    ArrayList<String> breeds;
    String sex;
    String description;
    String mix;
    String animal;
    String age;
    String size;
    ArrayList<String> options;
    String lastUpdate;
    String phone;
    String email;


//    empty constructor for parcelar and firebase

    public Pet() {
    }

    //constructor pet
    public Pet(String imageUrl, String id, ArrayList<String> breeds, String sex, String description, String mix, String animal, String age, String size, ArrayList<String> options, String lastUpdate, String phone, String email) {
        this.imageUrl = imageUrl;
        this.id = id;
        this.breeds = breeds;
        this.sex = sex;
        this.description = description;
        this.mix = mix;
        this.animal = animal;
        this.age = age;
        this.size = size;
        this.options = options;
        this.lastUpdate = lastUpdate;
        this.phone = phone;
        this.email = email;

    }

    //getter methods

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getBreeds() {
        return breeds;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

    public String getMix() {
        return mix;
    }

    public String getAnimal() {
        return animal;
    }

    public String getAge() {
        return age;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}




