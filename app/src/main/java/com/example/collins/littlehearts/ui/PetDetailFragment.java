package com.example.collins.littlehearts.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetailFragment extends Fragment {
    @Bind(R.id.petImageView)
    ImageView mImageLabel;
    @Bind(R.id.petNameTextView)
    TextView mNameLabel;
    @Bind(R.id.animalTextView) TextView mAnimalLabel;
    @Bind(R.id.lastUpdatedTextView) TextView mLastUpdatedLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.savePetButton) TextView mSavePetButton;

    private Pet mPet;

    public static PetDetailFragment newInstance(Pet pet) {
        PetDetailFragment petDetailFragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pet", Parcels.wrap(pet));
        petDetailFragment.setArguments(args);
        return petDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPet = Parcels.unwrap(getArguments().getParcelable("pet"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mPet.getImageUrls().get(0)).into(mImageLabel);

        mNameLabel.setText(mPet.getBreeds().get(0));
        mAnimalLabel.setText(mPet.getAge());
        mLastUpdatedLabel.setText("LastUpdated: " + mPet.getLastUpdate());
        mPhoneLabel.setText(mPet.getPhone());
        mAddressLabel.setText(mPet.getAddress());
        return view;
    }

}
