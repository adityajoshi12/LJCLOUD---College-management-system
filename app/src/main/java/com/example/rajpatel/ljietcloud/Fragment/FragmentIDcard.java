package com.example.rajpatel.ljietcloud.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.ModelClass.StudentProfile;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by himangi on 20/03/16.
 */
public class FragmentIDcard extends android.support.v4.app.Fragment {

    public StudentProfile profile;
    Firebase firebase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_icard, null);

        Firebase.setAndroidContext(getActivity());
        firebase = new Firebase("https://ljcloud-android.firebaseio.com/");
        initView(view);
        return view;
    }

    private void initView(View view) {

        final TextView name = (TextView) view.findViewById(R.id.stud_name);
        final TextView branch = (TextView) view.findViewById(R.id.stud_branch);
        final TextView enrollment = (TextView) view.findViewById(R.id.stud_enrollment);
        final ImageView profile = (ImageView) view.findViewById(R.id.profile);
        SharedPreferences myPrefs = getActivity().getSharedPreferences("loginSession", getActivity().MODE_PRIVATE);
        final String UUID = myPrefs.getString("UUID", "");

        firebase.child("Students").child(UUID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("Email", "" + dataSnapshot.child("email").getValue());
                Log.e("Name", "" + dataSnapshot.child("name").getValue());
                Log.e("enrollment", "" + dataSnapshot.child("enrollment").getValue());
                Log.e("Branch", "" + dataSnapshot.child("Branch").getValue());

                name.setText(dataSnapshot.child("name").getValue(String.class));
                branch.setText(dataSnapshot.child("Branch").getValue(String.class));
                enrollment.setText(dataSnapshot.child("enrollment").getValue(String.class));

                Picasso.with(getActivity()).load(dataSnapshot.child("img").getValue(String.class)).into(profile);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        firebase.keepSynced(true);

    }
}
