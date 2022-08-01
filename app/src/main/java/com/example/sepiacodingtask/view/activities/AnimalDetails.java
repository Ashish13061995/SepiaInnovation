package com.example.sepiacodingtask.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sepiacodingtask.R;
import com.example.sepiacodingtask.Utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnimalDetails extends AppCompatActivity {

    ImageView animal_image , btn_back;
    String Title,ImageUrl,ContentUrl,DateAdded;
    TextView date_txt,animal_txt;
    WebView animal_desc_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

        animal_image = findViewById(R.id.animal_image);
        animal_txt = findViewById(R.id.animal_txt);
        date_txt = findViewById(R.id.date_txt);
        animal_desc_web= findViewById(R.id.animal_desc_web);
        btn_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        Title = intent.getStringExtra("Title");
        ImageUrl = intent.getStringExtra("ImageUrl");
        ContentUrl = intent.getStringExtra("ContentUrl");
        DateAdded = intent.getStringExtra("DateAdded");




        Glide.with(this)
                .load(ImageUrl)
                .into(animal_image);

        animal_txt.setText(Title);
        date_txt.setText(Utils.getDateTime(DateAdded));

        animal_desc_web.setWebViewClient(new MyBrowser());
        animal_desc_web.getSettings().setLoadsImagesAutomatically(true);
        animal_desc_web.getSettings().setJavaScriptEnabled(true);
        animal_desc_web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        animal_desc_web.loadUrl(ContentUrl);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}