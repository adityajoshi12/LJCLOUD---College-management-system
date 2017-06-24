package com.example.rajpatel.ljietcloud.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajpatel.ljietcloud.DatabaseFile.DatabaseHelper;
import com.example.rajpatel.ljietcloud.DatabaseFile.SQLController;
import com.example.rajpatel.ljietcloud.R;

import java.sql.SQLException;

/**
 * Created by HimangiPatel on 01/02/16.
 */
public class DialogClickOption extends android.support.v4.app.DialogFragment {

    private SQLController dbcon;
    private String tt_ID;
    private long id;
    long ttid;
    String subName;
    private TextView ttidd;
    private TextView subname;
    private String TABLE_NAME;

    @SuppressLint("ValidFragment")
    public DialogClickOption(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ttid = Long.parseLong(bundle.getString("tt_id"));

        }

        dbcon = new SQLController(getActivity());
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogdelete, container, false);
        getDialog().setCancelable(false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setContentView(R.layout.fragment_subrecording);
        android.view.WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {

        final Button delete = (Button) view.findViewById(R.id.delete);
        final Button edit = (Button) view.findViewById(R.id.edit);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbcon.deleteData(ttid, TABLE_NAME);
                getTargetFragment().onActivityResult(0, Activity.RESULT_OK, new Intent());
                DialogClickOption.this.dismissAllowingStateLoss();
                dismiss();
            }
        });

//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                Dialogfragmentclass dialogfragmentclass = new Dialogfragmentclass(DatabaseHelper.TABLE_MONDAY);
//                dialogfragmentclass.setTargetFragment(DialogClickOption.this, 0);
//                dialogfragmentclass.show(fragmentManager, "show dialog");
//
//                String sunnew = Dialogfragmentclass.lectime + Dialogfragmentclass.lecname;
//
//                dbcon.updateData(ttid, TABLE_NAME, sunnew);
//                getTargetFragment().onActivityResult(0, Activity.RESULT_OK, new Intent());
//                DialogClickOption.this.dismissAllowingStateLoss();
//                dismiss();
//
//            }
//        });
    }
}
