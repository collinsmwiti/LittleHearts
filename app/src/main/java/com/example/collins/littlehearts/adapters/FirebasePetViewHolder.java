package com.example.collins.littlehearts.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.ui.PetDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by collins on 6/8/17.
 */

//class firebasepetviewholder
public class FirebasePetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;


    //constructor firebasepetviewholder
    public FirebasePetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    //inorder to display the saved contents to the user
    public void bindPet(Pet pet) {
        ImageView petImageView = (ImageView) mView.findViewById(R.id.petImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.petNameTextView);
        TextView animalTextView = (TextView) mView.findViewById(R.id.animalTextView);
        TextView lastUpdatedTextView = (TextView) mView.findViewById(R.id.lastUpdatedTextView);

        Picasso.with(mContext)
                .load(pet.getImageUrl())
                .into(petImageView);

            nameTextView.setText(pet.getBreeds().get(0));
            animalTextView.setText(pet.getAge());
            lastUpdatedTextView.setText("LastUpdated: " + pet.getLastUpdate());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Pet> pets = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pets.add(snapshot.getValue(Pet.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("pets", Parcels.wrap(pets));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
