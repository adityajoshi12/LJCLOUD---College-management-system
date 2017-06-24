package com.example.rajpatel.ljietcloud.Adapter;

/**
 * Created by HimangiPatel on 10/02/16.
 */
public class CourseModel {

    public String CourseName;
    public Integer CourseImage;

    public CourseModel(String courseName, Integer courseImage) {
        CourseName = courseName;
        CourseImage = courseImage;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public Integer getCourseImage() {
        return CourseImage;
    }

    public void setCourseImage(Integer courseImage) {
        CourseImage = courseImage;
    }
}
