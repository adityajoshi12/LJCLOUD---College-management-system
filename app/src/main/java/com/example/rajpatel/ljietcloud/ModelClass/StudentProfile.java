package com.example.rajpatel.ljietcloud.ModelClass;

import android.graphics.drawable.Drawable;

import com.orm.SugarRecord;

/**
 * Created by himangi on 20/03/16.
 */
public class StudentProfile extends SugarRecord {

    public String ENROLLMENTNUM;
    public String NAME;
    public String Branch;
    public Drawable dp;
    Boolean saveprofile;

    public StudentProfile() {
    }

    public StudentProfile(String ENROLLMENTNUM, String NAME, String branch, Boolean saveprofile) {
        this.ENROLLMENTNUM = ENROLLMENTNUM;
        this.NAME = NAME;
        Branch = branch;
        this.dp = dp;
        this.saveprofile = saveprofile;
    }

    public String getENROLLMENTNUM() {
        return ENROLLMENTNUM;
    }

    public void setENROLLMENTNUM(String ENROLLMENTNUM) {
        this.ENROLLMENTNUM = ENROLLMENTNUM;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public Drawable getDp() {
        return dp;
    }

    public void setDp(Drawable dp) {
        this.dp = dp;
    }

    public Boolean getSaveprofile() {
        return saveprofile;
    }

    public void setSaveprofile(Boolean saveprofile) {
        this.saveprofile = saveprofile;
    }
}
