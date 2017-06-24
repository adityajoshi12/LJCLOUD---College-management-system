package com.example.rajpatel.ljietcloud.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.Adapter.CourseModel;
import com.example.rajpatel.ljietcloud.ModelClass.DialogCourse;
import com.example.rajpatel.ljietcloud.R;

/**
 * Created by himangi on 12/02/16.
 */
public class DialogCourses extends DialogFragment {

    private int position;
    private DialogCourse courseModel;

    public DialogCourses(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogcourses, container, false);
        getDialog().setCancelable(false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setContentView(R.layout.fragment_subrecording);
        android.view.WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        final TextView txt_coursename = (TextView) view.findViewById(R.id.coursename);
        final TextView txt_level = (TextView) view.findViewById(R.id.level);
        final TextView txt_shift = (TextView) view.findViewById(R.id.shift);
        final TextView txt_duration = (TextView) view.findViewById(R.id.duration);
        final TextView txt_intake = (TextView) view.findViewById(R.id.intake);

        if (position==0){
            courseModel=new DialogCourse("Automobile Engineering","UG","First","4 Year","60");
            txt_coursename.setText(courseModel.getCOURSE_NAME());
            txt_level.setText(courseModel.getLEVEL());
            txt_shift.setText(courseModel.getSHIFT());
            txt_duration.setText(courseModel.getDURATION());
            txt_intake.setText(courseModel.getINTAKE());
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }else if (position==1){
            courseModel=new DialogCourse("","","","","");
        }


    }
}
