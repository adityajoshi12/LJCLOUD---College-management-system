package com.example.rajpatel.ljietcloud.Activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.Adapter.NavDrawerListAdapter;
import com.example.rajpatel.ljietcloud.Chat.ChatActivity;
import com.example.rajpatel.ljietcloud.Fragment.FragmentDailyUpdates;
import com.example.rajpatel.ljietcloud.Fragment.FragmentDoLogin;
import com.example.rajpatel.ljietcloud.Fragment.FragmentmyProfiile;
import com.example.rajpatel.ljietcloud.ModelClass.NavDrawerItem;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

public class LoginSuccessfully extends AppCompatActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private TextView name;
    private TextView branch;
    private TextView enrollment;
    private ImageView profile;
    private String mEmail;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successfully);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://ljcloud-android.firebaseio.com/");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue_light));
        }

        setTitle("Daily Updates");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new FragmentDailyUpdates()).commit();

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        name = (TextView) findViewById(R.id.stud_name);
        branch = (TextView) findViewById(R.id.stud_branch);
        enrollment = (TextView) findViewById(R.id.stud_enrollment);
        profile = (ImageView) findViewById(R.id.stud_profile);

        SharedPreferences myPrefs = getSharedPreferences("loginSession", MODE_PRIVATE);
        final String UUID = myPrefs.getString("UUID", "");
        mEmail = myPrefs.getString("email","");

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

                Picasso.with(LoginSuccessfully.this).load("https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-9/12313957_568651636620927_6778784351859232941_n.jpg?oh=3ab99d8af4f26106cacaab376d644b5f&oe=58BE4E17").into(profile);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        firebase.keepSynced(true);

        NavDrawerItem[] drawerItem = new NavDrawerItem[4];

        drawerItem[0] = new NavDrawerItem(R.mipmap.slideprofile, "My Profile");
        drawerItem[1] = new NavDrawerItem(R.mipmap.dailyupdates, "Daily Updates");
        drawerItem[2] = new NavDrawerItem(R.mipmap.collegecorner, "College Corner");
        drawerItem[3] = new NavDrawerItem(R.drawable.chat, "Chat with College's buddies");


        NavDrawerListAdapter adapter = new NavDrawerListAdapter(this, R.layout.slide_list, drawerItem);
        mDrawerList.setAdapter(adapter);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerToggle.setDrawerIndicatorEnabled(false);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawerToggle.setDrawerIndicatorEnabled(true);

            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new FragmentmyProfiile()).commit();

                    toolbar.setTitle("My Profile");
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                } else if (position == 1) {

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new FragmentDailyUpdates()).commit();
                    toolbar.setTitle("Daily Updates");
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                } else if (position == 2) {

                    Intent intent = new Intent(LoginSuccessfully.this, CollegeCorner.class);
                    startActivity(intent);
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                } else if (position == 3) {

                    Intent intent = new Intent(LoginSuccessfully.this, ChatActivity.class);
                    startActivity(intent);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else if (position == 4) {

                    SharedPreferences settings = getSharedPreferences(FragmentDoLogin.LOGINPREFERENCE, Context.MODE_PRIVATE);
                    settings.edit().clear().apply();

                    Intent intent = new Intent(LoginSuccessfully.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_student_login, menu);//Menu Resource, Menu


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.changePassword:

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_chage_password);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.TOP | Gravity.RIGHT;

                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);

                final EditText oldPassword = (EditText) dialog.findViewById(R.id.oldpass);
                final EditText newPassword = (EditText) dialog.findViewById(R.id.newpass);
                final Button changePass = (Button)dialog. findViewById(R.id.changePassword);

                changePass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        firebase.changePassword(mEmail, oldPassword.getText().toString(), newPassword.getText().toString(), new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {

                                dialog.dismiss();
                                oldPassword.setText("");
                                newPassword.setText("");

                                Snackbar.make(v,"Your password have been changed successfully",Snackbar.LENGTH_LONG).show();
                            }
                            @Override
                            public void onError(FirebaseError firebaseError) {

                                Snackbar.make(v,firebaseError.getMessage(),Snackbar.LENGTH_LONG).show();

                            }
                        });

                    }
                });


                dialog.show();
                break;

            case R.id.logout:

                SharedPreferences settings = getSharedPreferences(FragmentDoLogin.LOGINPREFERENCE, Context.MODE_PRIVATE);
                settings.edit().clear().apply();

                Intent intent = new Intent(LoginSuccessfully.this, HomeActivity.class);
                startActivity(intent);
                finish();

                break;
        }
        return true;
    }

}
