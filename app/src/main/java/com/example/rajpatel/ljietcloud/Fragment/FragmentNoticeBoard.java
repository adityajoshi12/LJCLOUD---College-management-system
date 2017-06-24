package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by himangi on 07/04/16.
 */
public class FragmentNoticeBoard extends android.support.v4.app.Fragment {

    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_noticeboard,null);
        initView(view);



        return view;
    }

    private void initView(View view){

        final TextView noticeBoard = (TextView) view.findViewById(R.id.notice);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        showProgressBar();
        Firebase.setAndroidContext(getActivity());
        Firebase ref = new Firebase("https://ljcloud-android.firebaseio.com/NoticeBoard");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String , String> map = dataSnapshot.getValue(Map.class);
                String notice=map.get("Notice");
                noticeBoard.setText(notice);
                hideProgressBar();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ref.keepSynced(true);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

}
