package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by Rajpatel on 19/11/15.
 */
public class FragmentSignUp extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.stud_signup,null);
        initView(view);
        return view;
    }

    private void initView(View view){

        final Button btnSignup=(Button)view.findViewById(R.id.signupbtn);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FragmentManager fragmentManager = getFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FragmentDoLogin fragmentSignUp = new FragmentDoLogin();
                fragmentTransaction.replace(R.id.container, fragmentSignUp);
                fragmentTransaction.commit();
            }
        });


    }
}
