package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Rajpatel on 24/12/15.
 */
public class FragmentLibrary extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_library,null);
        initView(view);
        return view;
    }

    private void initView(View view){

        final EditText book = (EditText) view.findViewById(R.id.bookname);
        final Button search = (Button) view.findViewById(R.id.search);
        final TextView bookpace = (TextView) view.findViewById(R.id.bookPlace);

        final Firebase firebase = new Firebase("https://ljcloud-himangi.firebaseio.com/Library");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                       String bookName = book.getText().toString().toLowerCase();

                        firebase.child(bookName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.getValue()==null){

                                    bookpace.setText("Sorry Result not found..!");
                                    book.setText("");

                                }else {

                                    bookpace.setText((String) dataSnapshot.getValue());
                                    book.setText("");

                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

    }
}
