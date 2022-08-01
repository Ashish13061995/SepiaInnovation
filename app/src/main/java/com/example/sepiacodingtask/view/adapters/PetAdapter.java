package com.example.sepiacodingtask.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.sepiacodingtask.R;
import com.example.sepiacodingtask.model.Pet;
import com.example.sepiacodingtask.view.activities.AnimalDetails;
import com.example.sepiacodingtask.view.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.Myviewholder>{

    List<Pet> petList = new ArrayList<Pet>();
    Context context;
    public PetAdapter(List<Pet> petList, Context context) {
        this.petList = petList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pet_list_item, viewGroup, false);
        return new Myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        Pet pet = petList.get(i);
        myviewholder.animal_txt.setText(pet.getTitle());

        Glide.with(context).load(pet.getImageUrl()).into(myviewholder.animal_image);

        myviewholder.animal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AnimalDetails.class);
                intent.putExtra("Title",pet.getTitle());
                intent.putExtra("ImageUrl",pet.getImageUrl());
                intent.putExtra("ContentUrl",pet.getContentUrl());
                intent.putExtra("DateAdded",pet.getDateAdded());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        ImageView animal_image;
        TextView animal_txt;
        CardView animal_card;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            animal_image = itemView.findViewById(R.id.animal_image);
            animal_txt= itemView.findViewById(R.id.animal_txt);
            animal_card = itemView.findViewById(R.id.animal_card);
        }
    }
}
