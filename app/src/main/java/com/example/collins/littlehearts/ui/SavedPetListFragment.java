package com.example.collins.littlehearts.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.adapters.FirebasePetListAdapter;
import com.example.collins.littlehearts.adapters.FirebasePetViewHolder;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.util.OnStartDragListener;
import com.example.collins.littlehearts.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
//class savedpetlistfragment
public class SavedPetListFragment extends Fragment implements OnStartDragListener {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private FirebasePetListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    public SavedPetListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_pet_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PETS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        //In line below, we change the 6th parameter 'this' to 'getActivity()'
        //because fragments do not have own context:
        mFirebaseAdapter = new FirebasePetListAdapter(Pet.class, R.layout.pet_list_item_drag, FirebasePetViewHolder.class,
                query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);

        //In line below, we change 'this' to 'getActivity()' because fragments do not have own context:
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        //adding observer to watch for changes, and notify our adapter when they occur.
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    //method is now public
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
