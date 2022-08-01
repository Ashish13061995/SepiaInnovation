package com.example.sepiacodingtask.presenter;

import android.content.Context;
import android.util.Log;

import com.example.sepiacodingtask.model.Pet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.sepiacodingtask.Utils.Utils.loadJSONFromAsset;

public class PetsListPresenter {
    Context context;
    PetsListResponse petsListResponse;

    public PetsListPresenter(Context context, PetsListResponse petsListResponse) {
        this.context = context;
        this.petsListResponse = petsListResponse;
    }

    public interface PetsListResponse{
        void success(ArrayList<Pet> response);
        void error(String response);

    }


    public void getPetList(String jsonFile) {

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context,jsonFile));
            JSONArray m_jArry = obj.getJSONArray("pets");

            ArrayList<Pet> petList = new ArrayList<Pet>();
            Pet pet;

            for (int i = 0; i < m_jArry.length(); i++) {
                pet = new Pet();

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String image_url = jo_inside.getString("image_url");
                String title = jo_inside.getString("title");
                String content_url = jo_inside.getString("content_url");
                String date_added = jo_inside.getString("date_added");
                Log.e("MainActivity","title: "+ title);

                pet.setImageUrl(image_url);
                pet.setTitle(title);
                pet.setContentUrl(content_url);
                pet.setDateAdded(date_added);
                petList.add(pet);

            }

            petsListResponse.success(petList);

        } catch (JSONException e) {
            e.printStackTrace();
            petsListResponse.error(e.toString());
        }
    }
}
