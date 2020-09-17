package com.example.asingtesting;

public class profileClass {
    String phonenumber,userName;

    void setprofile(String userName,String phonenumber)
    {
        this.phonenumber=phonenumber;
        this.userName =userName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getUserName() {
        return userName;
    }
}
