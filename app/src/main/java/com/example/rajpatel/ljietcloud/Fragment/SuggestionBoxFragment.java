package com.example.rajpatel.ljietcloud.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.Firebase;

/**
 * Created by himangi on 27/04/16.
 */
public class SuggestionBoxFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_suggestionbox, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {

        final EditText msg = (EditText) view.findViewById(R.id.msg);
        final Button send = (Button) view.findViewById(R.id.send);

        final Firebase firebase = new Firebase("https://ljcloud-himangi.firebaseio.com/");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebase.child("Suggestion Box").push().setValue(msg.getText().toString());
                msg.setText("");

                Snackbar snackbar;
                snackbar=Snackbar.make(v,"Your message was succesfully submitted...!",Snackbar.LENGTH_LONG);
                View view1 = snackbar.getView();
                view1.setBackgroundColor(getResources().getColor(R.color.color_blue_dark));
                snackbar.show();

            }
        });
    }

}
