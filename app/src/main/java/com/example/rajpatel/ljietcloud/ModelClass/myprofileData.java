package com.example.rajpatel.ljietcloud.ModelClass;

/**
 * Created by Rajpatel on 24/12/15.
 */
public class myprofileData {

    public String Name;
    public Integer image;

    public myprofileData(String name, Integer image) {
        Name = name;
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

