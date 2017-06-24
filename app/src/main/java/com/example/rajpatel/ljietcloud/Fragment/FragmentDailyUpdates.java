package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajpatel.ljietcloud.Adapter.DailyUpdatesadapter;
import com.example.rajpatel.ljietcloud.ModelClass.DailyUpdates;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by himangi on 26/04/16.
 */
public class FragmentDailyUpdates extends android.support.v4.app.Fragment implements View.OnClickListener{

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    EditText etName, etDesc;
    Button btnSave;
    ArrayList<DailyUpdates> updatesArrayList = new ArrayList<>();
    Firebase firebase;
    RecyclerView recyclerView;
    DailyUpdatesadapter myadapter;
    private static final String FIREBASE_URL = "https://ljcloud-android.firebaseio.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dailyupdates, container, false);
        firebase =new Firebase("https://ljcloud-himangi.firebaseio.com/");
        initView(view);
        return view;

    }

    private void initView(View view) {
        Firebase.setAndroidContext(getActivity());
        firebase = new Firebase(FIREBASE_URL);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton)view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.fab2);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myadapter = new DailyUpdatesadapter(getActivity(),updatesArrayList);
        recyclerView.setAdapter(myadapter);


        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


        refreshData();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:
                displayDialog();

                break;
            case R.id.fab2:

                Log.d("Raj", "Fab 2");
                break;
        }

    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    private void displayDialog(){

        SharedPreferences myPrefs = getActivity().getSharedPreferences("loginSession", getActivity().MODE_PRIVATE);
        final String UUID = myPrefs.getString("UUID", "");

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_dailyupdates);
        dialog.setTitle("Save Online.....");

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        etName = (EditText) dialog.findViewById(R.id.title);
        etDesc = (EditText) dialog.findViewById(R.id.description);
        btnSave = (Button) dialog.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveOnline(etName.getText().toString(), etDesc.getText().toString(),UUID);
                etName.setText("");
                etDesc.setText("");
                dialog.dismiss();

            }
        });


        dialog.show();
    }

    private void saveOnline(String name,String description,String UUID){

        DailyUpdates movie = new DailyUpdates();
        movie.setTitle(name);
        movie.setDescription(description);
        movie.setUser(UUID);
        firebase.child("DailyUpdates").child("Updates").push().setValue(movie);

    }

    private void refreshData(){

        firebase.child("DailyUpdates").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.d("child", "" + dataSnapshot.child("title").getValue());
                 getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                 getUpdates(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                getUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
        });

        firebase.keepSynced(true);
    }

    private void getUpdates(DataSnapshot dataSnapshot){

        updatesArrayList.clear();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            DailyUpdates movie;
            movie = snapshot.getValue(DailyUpdates.class);
            movie.setTitle(movie.getTitle());
            movie.setDescription(movie.getDescription());
            movie.setUser(movie.getUser());

            updatesArrayList.add(movie);
        }

        if (updatesArrayList.size()>0){

            myadapter.notifyDataSetChanged();


        }else {

            Toast.makeText(getActivity(),"No data added....", Toast.LENGTH_LONG).show();
        }
    }


}
