package com.example.rajpatel.ljietcloud.Chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HimangiPatel on 20/05/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    ArrayList<Chat> chatArrayList;
    private static final int SENDER=0;
    private static final int RECIPIENT=1;
    Firebase  firebase;

    public ChatAdapter(Context context, ArrayList<Chat> chatArrayList) {
        this.context = context;
        this.chatArrayList = chatArrayList;
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://ljcloud-android.firebaseio.com/");
    }

    @Override
    public int getItemViewType(int position) {

        SharedPreferences myPrefs = context.getSharedPreferences("loginSession", context.MODE_PRIVATE);
        final String UUID = myPrefs.getString("UUID", "");

        Log.e("ChatAdapter",""+chatArrayList.get(position).getAuthor());

        if(chatArrayList.get(position).getAuthor().equals(UUID)){
            Log.e("Adapter", " sender");
            return SENDER;
        }else {
            return RECIPIENT;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SENDER:
                View viewSender = inflater.inflate(R.layout.sender_message, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSender);
                break;
            case RECIPIENT:
                View viewRecipient = inflater.inflate(R.layout.recipient_message, viewGroup, false);
                viewHolder=new ViewHolderRecipient(viewRecipient);
                break;
            default:
                View viewSenderDefault = inflater.inflate(R.layout.sender_message, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSenderDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()){
            case SENDER:
                ViewHolderSender viewHolderSender=(ViewHolderSender)viewHolder;
                configureSenderView(viewHolderSender,position);
                break;
            case RECIPIENT:
                ViewHolderRecipient viewHolderRecipient=(ViewHolderRecipient)viewHolder;
                configureRecipientView(viewHolderRecipient,position);
                break;
        }


    }

    private void configureSenderView(final ViewHolderSender viewHolderSender, int position) {
        final Chat senderFireMessage=chatArrayList.get(position);

        firebase.child("Students").child(chatArrayList.get(position).getAuthor()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                viewHolderSender.getSenderMessageTextView().setText(dataSnapshot.child("name").getValue(String.class)+ " : " +senderFireMessage.getMessage());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void configureRecipientView(final ViewHolderRecipient viewHolderRecipient, int position) {
        final Chat recipientFireMessage=chatArrayList.get(position);

        firebase.child("Students").child(chatArrayList.get(position).getAuthor()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                viewHolderRecipient.getRecipientMessageTextView().setText(dataSnapshot.child("name").getValue(String.class)+" : "+recipientFireMessage.getMessage());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void refillAdapter(List<Chat> newFireChatMessage){

        /*add new message chat to list*/



    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }



      /*==============ViewHolder===========*/

    /*ViewHolder for Sender*/

    public class ViewHolderSender extends RecyclerView.ViewHolder {

        private TextView mSenderMessageTextView;
        private TextView mSender;

        public ViewHolderSender(View itemView) {
            super(itemView);
            mSenderMessageTextView=(TextView)itemView.findViewById(R.id.senderMessage);
            mSender = (TextView) itemView.findViewById(R.id.receiver);
        }

        public TextView getSenderMessageTextView() {
            return mSenderMessageTextView;
        }

        public void setSenderMessageTextView(TextView senderMessage) {
            mSenderMessageTextView = senderMessage;
        }
    }


    /*ViewHolder for Recipient*/
    public class ViewHolderRecipient extends RecyclerView.ViewHolder {

        private TextView mRecipientMessageTextView;
        private TextView mReciever;

        public ViewHolderRecipient(View itemView) {
            super(itemView);
            mRecipientMessageTextView=(TextView)itemView.findViewById(R.id.recipientMessage);
            mReciever = (TextView) itemView.findViewById(R.id.receiver);
        }

        public TextView getRecipientMessageTextView() {
            return mRecipientMessageTextView;
        }

        public void setRecipientMessageTextView(TextView recipientMessage) {
            mRecipientMessageTextView = recipientMessage;
        }
    }
}
