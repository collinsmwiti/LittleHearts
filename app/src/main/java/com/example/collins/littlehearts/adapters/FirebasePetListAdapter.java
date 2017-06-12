package com.example.collins.littlehearts.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.ui.PetDetailActivity;
import com.example.collins.littlehearts.util.ItemTouchHelperAdapter;
import com.example.collins.littlehearts.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by collins on 6/12/17.
 */

//As mentioned, let's make a custom adapter that inherits all functionality of the FirebaseRecyclerAdapter, and also includes its own code implementing the ItemTouchHelperAdapter. This will allow us to handle both FirebaseRecyclerAdapter and drag-and-drop functionalities in the same adapter.
public class FirebasePetListAdapter extends FirebaseRecyclerAdapter<Pet, FirebasePetViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Pet> mPets = new ArrayList<>();

    public FirebasePetListAdapter(Class<Pet> modelClass, int modelLayout,
                                  Class<FirebasePetViewHolder> viewHolderClass,
                                  Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mPets.add(dataSnapshot.getValue(Pet.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void populateViewHolder(final FirebasePetViewHolder viewHolder, Pet model, int position) {
        viewHolder.bindPet(model);
        viewHolder.mPetImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("pets", Parcels.wrap(mPets));
                mContext.startActivity(intent);
            }
        });
    }


    //    We use Collections.swap() to update the order of our mPets ArrayList items passing in the ArrayList of items and the starting and ending positions.
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mPets, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    //    We call the remove() method on our ArrayList of items in onItemDismiss() to remove the item from mPets at the given position.
    @Override
    public void onItemDismiss(int position) {
        mPets.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Pet pet : mPets) {
            int index = mPets.indexOf(pet);
            DatabaseReference ref = getRef(index);
            pet.setIndex(Integer.toString(index));
            ref.setValue(pet);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}


