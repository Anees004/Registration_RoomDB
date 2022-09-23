package com.example.registeration.RoomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


//............THis class is for creating table in the Room DB..........................


@Entity
public class SignupRecord implements Serializable {
    @PrimaryKey (autoGenerate = true)
    int signupid;

    public int getSignupid() {
        return signupid;
    }

    public void setSignupid(int signupid) {
        this.signupid = signupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public long getUsermobile_nbr() {
        return usermobile_nbr;
    }

    public void setUsermobile_nbr(long usermobile_nbr) {
        this.usermobile_nbr = usermobile_nbr;
    }

    String username,useremail,userpassword;
    long usermobile_nbr;
    public SignupRecord(String username, String useremail, String userpassword, long usermobile_nbr) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.usermobile_nbr = usermobile_nbr;
    }

    public SignupRecord(Integer id,String username, String useremail, String userpassword, long usermobile_nbr) {
        this.signupid = id;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.usermobile_nbr = usermobile_nbr;
    }

}
