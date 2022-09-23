package com.example.registeration.RoomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Stands for Databse Access object. All the data will be acesssed from this class
@Dao
public interface DAO {
    @Insert
    void Singupinsertion(SignupRecord obj);

    @Query("select * from SignupRecord where username like  :name and userpassword like :password")
    boolean CheckAccount(String name, String password);

    @Delete
    void Signupdelete(SignupRecord delobj);

    @Update
    void dbupdate(SignupRecord obj);
@Query("Select * from SignupRecord where username like :name and useremail like :email")
boolean getforget(String name,String email);
    //to check the entered data from the database
    @Query("Select * from SignupRecord where username like :name")
    boolean dbsamename(String name);
    @Query("Select * from SignupRecord where useremail like :email")
    boolean dbsameemail(String email);
    @Query("Select * from SignupRecord where usermobile_nbr like :mobile")
    boolean dbsamemobile(long mobile);


    @Query("Select * from SignUpRecord")
   LiveData< List<SignupRecord>> getalldata();
}
