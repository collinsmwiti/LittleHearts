package com.example.collins.littlehearts.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.adapters.FirebasePetListAdapter;
import com.example.collins.littlehearts.adapters.FirebasePetViewHolder;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.util.ItemTouchHelperAdapter;
import com.example.collins.littlehearts.util.OnStartDragListener;
import com.example.collins.littlehearts.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

//class saved restaurant list activity
public class SavedPetListActivity extends AppCompatActivity implements OnStartDragListener{

    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the content will be held at activity_pets xml
        setContentView(R.layout.activity_pets);
        ButterKnife.bind(this);

//        //to enable each and every user save his/her pet
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mPetReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_PETS)
//                .child(uid);

        setUpFirebaseAdapter();

//        mPetReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
//        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PETS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);;

        mFirebaseAdapter = new FirebasePetListAdapter(Pet.class,
                R.layout.pet_list_item_drag, FirebasePetViewHolder.class,
                query, this, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}

