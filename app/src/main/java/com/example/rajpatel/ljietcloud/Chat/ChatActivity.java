package com.example.rajpatel.ljietcloud.Chat;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajpatel.ljietcloud.ModelClass.DailyUpdates;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://ljcloud-android.firebaseio.com/";

    private String mUsername;

    private Firebase mFirebaseRef;

    private ValueEventListener mConnectedListener;

    private ArrayList<Chat> updatesArrayList = new ArrayList<>();

    private ProgressBar progressBar;
    private Chat chat;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // Make sure we have a mUsername
        setupUsername();
        setTitle("Chat with College's Buddy");
        // Setup our Firebase mFirebaseRef
        mFirebaseRef = new Firebase(FIREBASE_URL).child("chat");

        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) findViewById(R.id.messageInput);

        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    sendMessage();

                }

                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage();

            }
        });

        getChatList();

        mFirebaseRef.keepSynced(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        chatAdapter = new ChatAdapter(this, updatesArrayList);
        recyclerView.setAdapter(chatAdapter);


        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    hideProgressBar();
                } else {
                    showProgressBar();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });
    }


    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("loginSession", MODE_PRIVATE);
        mUsername = prefs.getString("UUID", null);
        if (mUsername == null) {
            Random r = new Random();
            // Assign a random user name if we don't have one saved.
            mUsername = "JavaUser" + r.nextInt(100000);
            prefs.edit().putString("username", mUsername).commit();
        }
    }

    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, mUsername);
            // Create a new, auto-generated child of that chat location, and save our chat data there
            mFirebaseRef.push().setValue(chat);
            inputText.setText("");
            getChatList();

        }
    }



    private void getChatList() {

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                updatesArrayList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Log.e("message", "" + snapshot.child("message").getValue());
                    Log.e("sender", "" + snapshot.child("author").getValue());

                    chat = new Chat();
                    chat.setMessage(snapshot.child("message").getValue(String.class));
                    chat.setAuthor(snapshot.child("author").getValue(String.class));
                    updatesArrayList.add(chat);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
