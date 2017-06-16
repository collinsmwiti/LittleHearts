package com.example.collins.littlehearts.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.collins.littlehearts.R.id.petImageView;

/**
 * Created by collins on 6/8/17.
 */

//class firebasepetviewholder
public class FirebasePetViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
    View mView;
    Context mContext;
    public ImageView mPetImageView;


    //constructor firebasepetviewholder
    public FirebasePetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    //inorder to display the saved contents to the user
    public void bindPet(Pet pet) {
        mPetImageView = (ImageView) mView.findViewById(petImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.petNameTextView);
        TextView animalTextView = (TextView) mView.findViewById(R.id.animalTextView);
        TextView lastUpdatedTextView = (TextView) mView.findViewById(R.id.lastUpdatedTextView);
        if (!pet.getImageUrl().contains("http")) {
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(pet.getImageUrl());
                mPetImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // This block of code should already exist, we're just moving it to the 'else' statement:
            Picasso.with(mContext)
                    .load(pet.getImageUrl())
                    .into(mPetImageView);
        }
            nameTextView.setText(pet.getBreeds().get(0));
            animalTextView.setText(pet.getAge());
            lastUpdatedTextView.setText("LastUpdated: " + pet.getLastUpdate());
    }

//    method responsible for decoding Base64:
public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
    byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
}
//    @Override
//    public void onClick(View view) {
//        final ArrayList<Pet> pets = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    pets.add(snapshot.getValue(Pet.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, PetDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("pets", Parcels.wrap(pets));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    //overriding the itemtouchhelperviewholder interface
    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
