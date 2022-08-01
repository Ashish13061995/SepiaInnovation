
package com.example.sepiacodingtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PetsList {

    @SerializedName("pets")
    private List<Pet> pets = null;

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
