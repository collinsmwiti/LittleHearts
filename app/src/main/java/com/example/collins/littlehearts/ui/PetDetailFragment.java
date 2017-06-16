package com.example.collins.littlehearts.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collins.littlehearts.Constants;
import com.example.collins.littlehearts.R;
import com.example.collins.littlehearts.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.petImageView) ImageView mImageLabel;
    @Bind(R.id.petNameTextView) TextView mNameLabel;
    @Bind(R.id.animalTextView) TextView mAnimalLabel;
    @Bind(R.id.lastUpdatedTextView) TextView mLastUpdatedLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.emailTextView) TextView mEmailLabel;
    @Bind(R.id.savePetButton) TextView mSavePetButton;

    private Pet mPet;
    private ArrayList<Pet> mPets;
    private int mPosition;
    private String mSource;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    public static PetDetailFragment newInstance(ArrayList<Pet> pets, Integer position, String source) {
        PetDetailFragment petDetailFragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_PETS, Parcels.wrap(pets));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);

        petDetailFragment.setArguments(args);
        return petDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPets = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_PETS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mPet = mPets.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);

        if (!mPet.getImageUrl().contains("http")) {
            try {
                Bitmap image = decodeFromFirebaseBase64(mPet.getImageUrl());
                mImageLabel.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Picasso.with(view.getContext())
                    .load(mPet.getImageUrl())
                    .into(mImageLabel);
        }

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSavePetButton.setVisibility(View.GONE);
        } else {
            mSavePetButton.setOnClickListener(this);
        }

        mNameLabel.setText(mPet.getBreeds().get(0));
        mAnimalLabel.setText(mPet.getAge());
        mLastUpdatedLabel.setText(mPet.getLastUpdate());
        mPhoneLabel.setText(mPet.getPhone());
        mEmailLabel.setText(mPet.getEmail());

        mPhoneLabel.setOnClickListener(this);
        mEmailLabel.setOnClickListener(this);

        return view;
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference petRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_PETS)
                    .child(uid);

//            petRef.push().setValue(mPet);

            DatabaseReference pushRef = petRef.push();
            String pushId = pushRef.getKey();
            mPet.setPushId(pushId);
            pushRef.setValue(mPet);

            Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
        }

    }

    //logic to handle user interactions with the menu options:
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (mSource.equals(Constants.SOURCE_SAVED)) {
            inflater.inflate(R.menu.menu_photo, menu);
        } else {
            inflater.inflate(R.menu.menu_main, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }

//    method called when the user selects the camera icon from their menu:
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

//    startActivityForResult() will automatically trigger the callback method onActivityResult()
// when the result of our activity is available. (In our case, a picture the user has taken).
// I'll override this method in order to snag users picture:
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        mImageLabel.setImageBitmap(imageBitmap);
        encodeBitmapAndSaveToFirebase(imageBitmap);
    }
}

//saving encoded images
public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
    String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    DatabaseReference ref = FirebaseDatabase.getInstance()
            .getReference(Constants.FIREBASE_CHILD_PETS)
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .child(mPet.getPushId())
            .child("imageUrl");
    ref.setValue(imageEncoded);
}
}
