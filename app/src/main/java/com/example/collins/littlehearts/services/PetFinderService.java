//package
package com.example.collins.littlehearts.services;

//imports

import android.util.Log;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.models.Pet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by collins on 6/2/17.
 */

//class PetFinder
public class PetFinderService {
    //to enhance connection to the API
    private static OkHttpClient client = new OkHttpClient();
    public static void adoptPets(String location, Callback callback) {


        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PET_FINDER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("key", Constants.PET_FINDER_API_KEY );
        urlBuilder.addQueryParameter(Constants.PET_FINDER_LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter("format", "json");

        String url = urlBuilder.build().toString();
//        String url = urlBuilder.build().toString() ++ "&format=json&location=" + location;



            Request request = new Request.Builder()
                    .url(url)
                    .build();

        Call call = client.newCall(request);
        call.enqueue(callback);


    }

    // to enhance data to be displayed inform of an arraylist
    public ArrayList<Pet> processResults(Response response) {
        ArrayList<Pet> pets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject petFinderJSON = new JSONObject(jsonData);
                JSONArray petJSON = petFinderJSON.getJSONObject("petfinder").getJSONObject("pets").getJSONArray("pet");

                for (int i = 0; i < petJSON.length(); i++) {
                    //arraylist for urls
//                    ArrayList<String> imageUrls = new ArrayList<>();
//
//                    JSONArray imageUrlsJSON = petJSON.getJSONObject(i).getJSONObject("media").getJSONObject("photos").getJSONArray("photo");
//                    for (int y = 0; y < imageUrlsJSON.length(); y++) {
//                        imageUrls.add(imageUrlsJSON.getJSONObject(y).optString("$t").toString());

//                    }
                    //string imageurl
                    String imageUrl = petJSON.getJSONObject(i).getJSONObject("media").getJSONObject("photos").getJSONArray("photo")
                            .getJSONObject(2).optString("$t");
                    //display id
                    String id = petJSON.getJSONObject(i).getJSONObject("id").optString("$t");
                    //arraylists for breeds
                    ArrayList<String> breeds = new ArrayList<>();
                    JSONArray breedsJSON = petJSON.getJSONObject(i).getJSONObject("breeds").optJSONArray("breed");
                    if (breedsJSON == null) {
                        String breed = petJSON.getJSONObject(i).getJSONObject("breeds").optJSONObject("breed").optString("$t");
                        breeds.add(breed);
                    } else {
                        for (int y = 0; y < breedsJSON.length(); y++) {
                            breeds.add(breedsJSON.getJSONObject(y).optString("$t").toString());
                        }
                    }
                    //display sex
                    String sex = petJSON.getJSONObject(i).getJSONObject("sex").optString("$t");
                    //display description
                    String description = petJSON.getJSONObject(i).getJSONObject("description").optString("$t");
                    //display sex
                    String mix = petJSON.getJSONObject(i).getJSONObject("mix").optString("$t");
                    //display animal
                    String animal = petJSON.getJSONObject(i).getJSONObject("animal").optString("$t");
                    //display age
                    String age = petJSON.getJSONObject(i).getJSONObject("age").optString("$t");
                    //display size
                    String size = petJSON.getJSONObject(i).getJSONObject("size").optString("$t");
                    //display options
                    ArrayList<String> options = new ArrayList<>();
                    JSONArray optionsJSON = petJSON.getJSONObject(i).getJSONObject("options").getJSONArray("option");
                    for (int y = 0; y < optionsJSON.length(); y++) {
                        options.add(optionsJSON.getJSONObject(y).optString("$t").toString());
                    }
                    //display lastupdate
                    String lastUpdate = petJSON.getJSONObject(i).getJSONObject("lastUpdate").optString("$t");
                    String phone = petJSON.getJSONObject(i).getJSONObject("contact").getJSONObject("phone").optString("$t", "No phone found");
                    String email = petJSON.getJSONObject(i).getJSONObject("contact").getJSONObject("email").optString("$t");

                    Pet pet = new Pet(imageUrl, id, breeds, sex, description, mix, animal, age, size, options, lastUpdate, phone, email);
                    pets.add(pet);
                }

            }
        } catch (IOException e) {
            Log.e("log_tag", "Error parsing data "+e.toString());
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return  pets;
    }
}
