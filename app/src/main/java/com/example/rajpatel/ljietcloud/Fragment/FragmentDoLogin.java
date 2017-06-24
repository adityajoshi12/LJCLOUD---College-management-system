package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.rajpatel.ljietcloud.Activity.LoginSuccessfully;
import com.example.rajpatel.ljietcloud.ProgressGenerator;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * Created by Rajpatel on 19/11/15.
 */
public class FragmentDoLogin extends Fragment implements ProgressGenerator.OnCompleteListener {

    public static String  LOGINPREFERENCE = "loginSession";
    private SharedPreferences preferencesLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stud_login, null);

        initView(view);
        return view;

    }

    private void initView(final View view) {

        final EditText email = (EditText) view.findViewById(R.id.etEmail);
        final EditText pass = (EditText) view.findViewById(R.id.etPass);
        final ActionProcessButton btnlogin = (ActionProcessButton) view.findViewById(R.id.btn_login);
        final TextView admin = (TextView) view.findViewById(R.id.forgotPassword);

        final Firebase mLofinRef = new Firebase("https://ljcloud-android.firebaseio.com/");

        preferencesLogin = getActivity().getSharedPreferences(LOGINPREFERENCE, Context.MODE_PRIVATE);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (email!=null){

                    mLofinRef.resetPassword(email.getText().toString(), new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {

                            Snackbar.make(v,"Your new Password have been sent to your email id",Snackbar.LENGTH_LONG).show();
                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {

                            Snackbar.make(v,firebaseError.getMessage(),Snackbar.LENGTH_LONG).show();

                        }
                    });

                }else {

                    Snackbar.make(v,"Enter Your Email ID",Snackbar.LENGTH_LONG).show();
                }

            }
        });


        final ProgressGenerator progressGenerator = new ProgressGenerator(this) {
            @Override
            public void onComplete() {
                Toast.makeText(getActivity(), "Loading Complete, button is disabled", Toast.LENGTH_LONG).show();
            }
        };


                    btnlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            progressGenerator.start(btnlogin);

                            mLofinRef.authWithPassword(email.getText().toString(), pass.getText().toString(), new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {

                                    Intent intent = new Intent(getActivity(), LoginSuccessfully.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                    SharedPreferences.Editor editor = preferencesLogin.edit();
                                    editor.putString("email",email.getText().toString());
                                    editor.putString("pass",pass.getText().toString());
                                    editor.putString("UUID",authData.getUid());
                                    editor.putBoolean("isLogin", true);
                                    editor.apply();

                                    Log.e("uuid",authData.getUid());


                                }

                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {

                                    Snackbar.make(view,firebaseError.getMessage().toString(),Snackbar.LENGTH_LONG).show();

                                }
                            });



                        }
                    });
                }

    @Override
    public void onComplete() {

    }
}
