
package com.example.sepiacodingtask.model;
import com.google.gson.annotations.SerializedName;


public class Settings {

    @SerializedName("settings")
    private Settings__1 settings;

    public Settings__1 getSettings() {
        return settings;
    }

    public void setSettings(Settings__1 settings) {
        this.settings = settings;
    }

}
