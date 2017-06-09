package com.example.collins.littlehearts.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.adapters.FirebasePetViewHolder;
import com.example.collins.littlehearts.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

//class saved restaurant list activity
public class SavedPetListActivity extends AppCompatActivity {

    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the content will be held at activity_pets xml
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

        mPetReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Pet, FirebasePetViewHolder>(Pet.class, R.layout.pet_list_item, FirebasePetViewHolder.class, mPetReference) {

            @Override
            protected void populateViewHolder(FirebasePetViewHolder viewHolder, Pet model, int position) {
                viewHolder.bindPet(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
