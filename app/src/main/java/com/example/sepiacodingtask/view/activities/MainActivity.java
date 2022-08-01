package com.example.sepiacodingtask.view.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sepiacodingtask.R;
import com.example.sepiacodingtask.model.Pet;
import com.example.sepiacodingtask.presenter.PetsListPresenter;
import com.example.sepiacodingtask.view.adapters.PetAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import static com.example.sepiacodingtask.Utils.Utils.loadJSONFromAsset;

public class MainActivity extends AppCompatActivity implements PetsListPresenter.PetsListResponse {

    ArrayList<Pet> petList ;
    PetAdapter petAdapter;
    RecyclerView pets_recyclerview;
    PetsListPresenter petsListPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petsListPresenter = new PetsListPresenter(MainActivity.this,this);
        pets_recyclerview = findViewById(R.id.pets_recyclerview);


        getWorkTime("config.json");
        petsListPresenter.getPetList("pets_list.json");

        // Considered Monday-Friday 9Am-6Pm are working Hours
        checkWorkingTime();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkWorkingTime() {
        Date currentTime = Calendar.getInstance().getTime();
        Log.e("MainActivity", "currentTime: "+currentTime );

        Calendar cal = Calendar.getInstance(); //Create Calendar-Object
        cal.setTime(new Date());               //Set the Calendar to now
        int hour = cal.get(Calendar.HOUR_OF_DAY); //Get the hour from the calendar

        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        String dayName = dow.getDisplayName(TextStyle.NARROW, Locale.ENGLISH);
        Log.e("MainActivity", "dayName: "+dayName);

        if (!dayName.equals("S")){

            if(hour >= 9 && hour <= 18)              // Check if hour is between 9 am and 6pm
            {
                // working
            }else {
                // Not working
                dialogNotWOrkingHours();
            }

        }else {
            // Not working
            dialogNotWOrkingHours();
        }
    }

    private void dialogNotWOrkingHours() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_no_working);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((ImageView) dialog.findViewById(R.id.img_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void getWorkTime(String jsonFile) {

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(getApplicationContext(), jsonFile));
            JSONObject mainObject = obj.getJSONObject("settings");
            String workHours = mainObject.getString("workHours");
            Log.e("MainActivity","workHours: "+ workHours);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showPetsList() {
        GridLayoutManager horizontalLayoutManager1 = new GridLayoutManager(this, 2);
        pets_recyclerview.setLayoutManager(horizontalLayoutManager1);
        pets_recyclerview.setItemAnimator(new DefaultItemAnimator());
        pets_recyclerview.setHasFixedSize(true);

        petAdapter = new PetAdapter(petList,this);
        pets_recyclerview.setAdapter(petAdapter);

    }


    @Override
    public void success(ArrayList<Pet> response) {
        petList = new ArrayList<Pet>();
        petList.addAll(response);
        showPetsList();
    }

    @Override
    public void error(String response) {
        Toast.makeText(this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
    }
}