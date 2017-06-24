package com.example.rajpatel.ljietcloud.ModelClass;

/**
 * Created by himangi on 12/02/16.
 */
public class DialogCourse {

    String COURSE_NAME;
    String LEVEL;
    String SHIFT;
    String DURATION;
    String INTAKE;

    public DialogCourse(String COURSE_NAME, String LEVEL, String SHIFT, String DURATION, String INTAKE) {
        this.COURSE_NAME = COURSE_NAME;
        this.LEVEL = LEVEL;
        this.SHIFT = SHIFT;
        this.DURATION = DURATION;
        this.INTAKE = INTAKE;
    }

    public String getCOURSE_NAME() {
        return COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(String LEVEL) {
        this.LEVEL = LEVEL;
    }

    public String getSHIFT() {
        return SHIFT;
    }

    public void setSHIFT(String SHIFT) {
        this.SHIFT = SHIFT;
    }

    public String getDURATION() {
        return DURATION;
    }

    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }

    public String getINTAKE() {
        return INTAKE;
    }

    public void setINTAKE(String INTAKE) {
        this.INTAKE = INTAKE;
    }
}
