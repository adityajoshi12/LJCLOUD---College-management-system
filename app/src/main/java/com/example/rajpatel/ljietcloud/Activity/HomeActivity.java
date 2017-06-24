package com.example.rajpatel.ljietcloud.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.rajpatel.ljietcloud.Adapter.HomeGridCustomAdpter;
import com.example.rajpatel.ljietcloud.Fragment.FragmentDoLogin;
import com.example.rajpatel.ljietcloud.R;

public class HomeActivity extends Activity {

    private final Integer[] imageuser_login = {R.drawable.loginlogo, R.drawable.loginlogo, R.drawable.loginlogo, R.drawable.loginlogo, R.drawable.gallary, R.drawable.website, R.drawable.loginlogo, R.drawable.loginlogo, R.drawable.loginlogo};
    private GridView gridView = null;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences(FragmentDoLogin.LOGINPREFERENCE, MODE_PRIVATE);
        final Boolean isLoggedin = preferences.getBoolean("isLogin", false);

        gridView = (GridView) findViewById(R.id.gridlogin);
        gridView.setAdapter(new HomeGridCustomAdpter(getResources().getStringArray(R.array.user_type), imageuser_login, this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (gridView.getItemIdAtPosition(position) == 0) {

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else if (position == 2) {
                    Intent intent = new Intent(HomeActivity.this, AdmissionInquiry.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(HomeActivity.this, VisitWebSite.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(HomeActivity.this, GallaryView.class);
                    startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(HomeActivity.this, CoursesinLj.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(HomeActivity.this, ReachUs.class);
                    startActivity(intent);
                } else if (position == 7) {
                    Intent intent = new Intent(HomeActivity.this, AboutUS.class);
                    startActivity(intent);
                }
            }
        });
    }
}
