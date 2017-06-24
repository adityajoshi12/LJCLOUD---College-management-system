package com.example.rajpatel.ljietcloud.ModelClass;

/**
 * Created by himangi on 27/04/16.
 */
public class Admission {

    String name;
    String email;
    String PhoneNumber;
    String city;
    String Branch;
    String QueryMsg;


    public Admission() {
    }

    public Admission(String name, String email, String phoneNumber, String city, String branch, String queryMsg) {
        this.name = name;
        this.email = email;
        PhoneNumber = phoneNumber;
        this.city = city;
        Branch = branch;
        QueryMsg = queryMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getQueryMsg() {
        return QueryMsg;
    }

    public void setQueryMsg(String queryMsg) {
        QueryMsg = queryMsg;
    }
}
