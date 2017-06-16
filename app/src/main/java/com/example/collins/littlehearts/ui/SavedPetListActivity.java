package com.example.collins.littlehearts.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.collins.littlehearts.R;

//class saved restaurant list activity
public class SavedPetListActivity extends AppCompatActivity {

//    private DatabaseReference mPetReference;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//    private ItemTouchHelper mItemTouchHelper;
//
//    @Bind(R.id.recyclerView)
//    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the content will be held at activity_pets xml
        setContentView(R.layout.activity_saved_pet_list);
//        ButterKnife.bind(this);
//
////        //to enable each and every user save his/her pet
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        String uid = user.getUid();
////
////        mPetReference = FirebaseDatabase
////                .getInstance()
////                .getReference(Constants.FIREBASE_CHILD_PETS)
////                .child(uid);
//
//        setUpFirebaseAdapter();
//
////        mPetReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PETS);
////        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        Query query = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_PETS)
//                .child(uid)
//                .orderByChild(Constants.FIREBASE_QUERY_INDEX);;
//
//        mFirebaseAdapter = new FirebasePetListAdapter(Pet.class,
//                R.layout.pet_list_item_drag, FirebasePetViewHolder.class,
//                query, this, this);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//
//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHelper.startDrag(viewHolder);
    }
}

