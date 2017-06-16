package com.example.collins.littlehearts.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.example.collins.littlehearts.ui.PetDetailActivity;
import com.example.collins.littlehearts.ui.PetDetailFragment;
import com.example.collins.littlehearts.util.OnPetSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by collins on 6/3/17.
 */

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {
    private ArrayList<Pet> mPets = new ArrayList<>();
    private Context mContext;
    private OnPetSelectedListener mOnPetSelectedListener;

    public PetListAdapter(Context context, ArrayList<Pet> pets, OnPetSelectedListener petSelectedListener) {
        mContext = context;
        mPets = pets;
        mOnPetSelectedListener = petSelectedListener;
    }

    @Override
    public PetListAdapter.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
        PetViewHolder viewHolder = new PetViewHolder(view, mPets, mOnPetSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetListAdapter.PetViewHolder holder, int position) {
        holder.bindPet(mPets.get(position));
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.petImageView)
        ImageView mPetImageView;
        @Bind(R.id.petNameTextView)
        TextView mPetNameTextView;
        @Bind(R.id.animalTextView) TextView mAnimalTextView;
        @Bind(R.id.lastUpdatedTextView) TextView mLastUpdatedTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Pet> mPets = new ArrayList<>();
        private OnPetSelectedListener mPetSelectedListener;

        public PetViewHolder(View itemView, ArrayList<Pet> pets, OnPetSelectedListener petSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            //Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mPets = pets;
            mOnPetSelectedListener = petSelectedListener;

            //Checks if the recorded orientation matches Android's landscape configuration/
            //if so, we create a new DetailFragment to dispaly in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        //Takes position of pet in list as parameter:
        private void createDetailFragment(int position) {
            // Creates new PetDetailFragment with the given position:
            PetDetailFragment detailFragment = PetDetailFragment.newInstance(mPets, position, Constants.SOURCE_FIND);
            //Gathers necessary components to replace the FrameLayout in the layout with the PetDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            //Replaces the FrameLayout with the PetDetailFragment:
            ft.replace(R.id.petDetailContainer, detailFragment);
            //Commits these changes:
            ft.commit();
        }

        @Override
        public void onClick(View v) {
//            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
//            mPetSelectedListener.onPetSelected(itemPosition, mPets, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, PetDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_PETS, Parcels.wrap(mPets));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }


        public void bindPet(Pet pet) {
            Picasso.with(mContext).load(pet.getImageUrl()).into(mPetImageView);
            mPetNameTextView.setText(pet.getBreeds().get(0));
            mAnimalTextView.setText(pet.getAge());
            mLastUpdatedTextView.setText("LastUpdated: " + pet.getLastUpdate());
        }
    }
}
