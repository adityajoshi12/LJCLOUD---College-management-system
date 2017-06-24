package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajpatel.ljietcloud.Adapter.RecordingAdapter;
import com.example.rajpatel.ljietcloud.DatabaseFile.DatabaseHelper;
import com.example.rajpatel.ljietcloud.DatabaseFile.SQLController;
import com.example.rajpatel.ljietcloud.ModelClass.ClassRecording;
import com.example.rajpatel.ljietcloud.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Created by HimangiPatel on 29/01/16.
 */
public class FragmentClassRecords extends android.support.v4.app.Fragment {

    private String outputFile = null;
    private MediaRecorder myAudioRecorder;
    SQLController dbcon;
    private TextView sub_id;
    private ListView listView;
    private RecordingAdapter adapter;
    private EditText lec_name;
    private String lec_namee;
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    private Long idd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_create_your_day, container, false);
        dbcon = new SQLController(getActivity());
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        listView = (ListView) view.findViewById(R.id.list_sub);

        readRecording();



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sub_id = (TextView) view.findViewById(R.id.sub_id);
                idd = Long.parseLong(sub_id.getText().toString());
                Log.e("Id", "" + idd);
                displayDeleteDialog();
                readRecording();
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRecordingDialog();
            }
        });
    }



    private void displayDeleteDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogdelete);
        android.view.WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final Button delete = (Button) dialog.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Id", "" + idd);
                dbcon.deleteData(idd, DatabaseHelper.TABLE_CLASSRECORD);
                readRecording();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //----------------------------Dialog-----------------
    private void displayRecordingDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_subrecording);
        android.view.WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final ImageView close = (ImageView) dialog.findViewById(R.id.close);
        final ImageView record = (ImageView) dialog.findViewById(R.id.record);
        final ImageView pause = (ImageView) dialog.findViewById(R.id.pause);
        final ImageView stop = (ImageView) dialog.findViewById(R.id.stop);
        final ImageView play = (ImageView) dialog.findViewById(R.id.play);
        lec_name = (EditText) dialog.findViewById(R.id.lec_name);
        final EditText short_notes = (EditText) dialog.findViewById(R.id.short_notes);
        final Button save = (Button) dialog.findViewById(R.id.save_recording);
        dialog.show();

        settings();

        stop.setEnabled(false);
        play.setEnabled(false);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



           save.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   lec_namee = lec_name.getText().toString();
                   final String lec_notes = short_notes.getText().toString();
                   if (lec_namee.equals("")||lec_notes.equals("")){
                       Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();

                   } else {
                       dbcon.insertClassRecord(lec_namee, lec_notes,outputFile, DatabaseHelper.TABLE_CLASSRECORD);
                       readRecording();
                       dialog.dismiss();
                      }
               }
           });


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getActivity(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;

                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getActivity(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
            }
        });




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();
                try {
                    m.setDataSource(outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();
                Toast.makeText(getActivity(), "Playing audio", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void settings() {

        Log.d("subname",""+generateRandomString());
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+generateRandomString()+".3gp";;


        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }


    private void readRecording() {
        List<ClassRecording> allRecord = dbcon.getAllBooks();

        adapter= new RecordingAdapter(getActivity(),allRecord);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        Log.d("path", "" + DatabaseHelper.SUB_RECORDPATH);

        adapter.notifyDataSetChanged();
    }



    /**
     * This method generates random string
     * @return
     */
    public String generateRandomString(){

        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number =getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

}


