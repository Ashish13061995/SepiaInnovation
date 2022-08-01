package com.example.sepiacodingtask.Utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {



    public static String loadJSONFromAsset(Context context,String jsonFile) {
        String json = null;
        try {
            InputStream is =context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String getDateTime(String dateAdded) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date d = null;
        String formattedDate="";
        String formattedTime ="";
        try
        {
            d = input.parse(dateAdded);
            formattedDate = output.format(d);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        Date dt;
        try {
            dt = input.parse(dateAdded);
            formattedTime = sdfs.format(dt);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate +" "+formattedTime ;
    }
}
