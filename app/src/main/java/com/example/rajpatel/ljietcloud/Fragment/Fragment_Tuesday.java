package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.Adapter.AlternateRowCursorAdapter;
import com.example.rajpatel.ljietcloud.DatabaseFile.DatabaseHelper;
import com.example.rajpatel.ljietcloud.DatabaseFile.SQLController;
import com.example.rajpatel.ljietcloud.R;

import java.sql.SQLException;

/**
 * Created by Rajpatel on 27/12/15.
 */
public class Fragment_Tuesday extends android.support.v4.app.Fragment {

    private SQLController dbcon;
    private AlternateRowCursorAdapter adapter;
    private boolean isRegister = false;
    private ListView listView;
    private TextView ttid;
    private TextView subname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mondaymain, container, false);
        dbcon = new SQLController(getActivity());
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initView(rootView);
        return rootView;
    }

    public void initView(View view) {

        listView = (ListView) view.findViewById(R.id.list_sub);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Dialogfragmentclass dialogfragmentclass = new Dialogfragmentclass(DatabaseHelper.TABLE_TUESDAY);
                dialogfragmentclass.setTargetFragment(Fragment_Tuesday.this, 0);
                dialogfragmentclass.show(fragmentManager, "show dialog");

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager = getFragmentManager();
                DialogClickOption dialogClickOption = new DialogClickOption(DatabaseHelper.TABLE_TUESDAY);
                dialogClickOption.show(fragmentManager, "delete list");
                dialogClickOption.setTargetFragment(Fragment_Tuesday.this, 0);

                subname = (TextView) view.findViewById(R.id.sub_name);
                ttid = (TextView) view.findViewById(R.id.tt_id);

                String ttID_val = ttid.getText().toString();
                String subName_val = subname.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("tt_id", ttID_val);
                bundle.putString("sub_name", subName_val);
                dialogClickOption.setArguments(bundle);
            }
        });
    }

    public void setData() {
        Cursor cursor = dbcon.readData(DatabaseHelper.TABLE_TUESDAY);
        String[] from = new String[]{DatabaseHelper.TT_ID,DatabaseHelper.SUB_NAME,DatabaseHelper.SUB_TIME};
        int[] to = new int[]{R.id.tt_id, R.id.sub_name,R.id.sub_time};
        adapter = new AlternateRowCursorAdapter(
                getActivity(), R.layout.lec_list, cursor, from, to);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 0){
                setData();
            }
        }
    }
}
