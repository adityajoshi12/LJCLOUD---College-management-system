package com.example.rajpatel.ljietcloud.Fragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.rajpatel.ljietcloud.Adapter.EventsFirebaseadapter;
import com.example.rajpatel.ljietcloud.ModelClass.EventsFirebasemodel;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.Firebase;

/**
 * Created by himangi on 10/04/16.
 */
public class FragmentEvents extends android.support.v4.app.Fragment {

    private ListView listView;

    private EventsFirebaseadapter adapter;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);
        initView(view);
        return view;

    }

    public void initView(View view) {

        listView = (ListView) view.findViewById(R.id.list);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        showProgressBar();

        Firebase.setAndroidContext(getActivity());
        Firebase firebase = new Firebase("https://ljcloud-himangi.firebaseio.com/Events");

        adapter = new EventsFirebaseadapter(getActivity(), EventsFirebasemodel.class, R.layout.item_eventss, firebase,
                getActivity());
        listView.setAdapter(adapter);



        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(adapter.getCount() - 1);
                hideProgressBar();
            }
        });

        firebase.keepSynced(true);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }
}
