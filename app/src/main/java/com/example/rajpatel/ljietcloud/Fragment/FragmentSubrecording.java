package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rajpatel.ljietcloud.R;

/**
 * Created by HimangiPatel on 21/01/16.
 **/

public class FragmentSubrecording extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_subrecording, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {}
}
