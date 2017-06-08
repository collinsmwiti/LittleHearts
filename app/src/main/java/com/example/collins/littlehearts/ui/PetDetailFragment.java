package com.example.collins.littlehearts.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.petImageView)
    ImageView mImageLabel;
    @Bind(R.id.petNameTextView)
    TextView mNameLabel;
    @Bind(R.id.animalTextView) TextView mAnimalLabel;
    @Bind(R.id.lastUpdatedTextView) TextView mLastUpdatedLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.emailTextView) TextView mEmailLabel;
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

        Picasso.with(view.getContext())
                .load(mPet.getImageUrl())
                .into(mImageLabel);

        mNameLabel.setText(mPet.getBreeds().get(0));
        mAnimalLabel.setText(mPet.getAge());
        mLastUpdatedLabel.setText("LastUpdated: " + mPet.getLastUpdate());
        mPhoneLabel.setOnClickListener(this);
        mEmailLabel.setOnClickListener(this);

        //adding a listener to save pets button in order for the users to save their pets
        mSavePetButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPet.getPhone()));
            startActivity(phoneIntent);
        }

        if (v == mEmailLabel) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","haventohome@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }

        if (v == mSavePetButton) {
            DatabaseReference petRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETS);
            petRef.push().setValue(mPet);
            Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
        }

    }

}
