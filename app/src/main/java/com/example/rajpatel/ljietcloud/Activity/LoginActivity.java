package com.example.rajpatel.ljietcloud.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.rajpatel.ljietcloud.Fragment.FragmentDoLogin;
import com.example.rajpatel.ljietcloud.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue_light));
        }
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final FragmentDoLogin loginFragment = new FragmentDoLogin();
        fragmentTransaction.replace(R.id.container, loginFragment);
        fragmentTransaction.commit();

    }


}
