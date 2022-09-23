package com.example.registeration.RoomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.registeration.classes.classmainstore;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private classmainstore repobj;
    private LiveData<List<SignupRecord>> allrec;
    public ViewModel(@NonNull Application application) {
        super(application);
        allrec = new MutableLiveData<>();
        repobj = new classmainstore(application);
        allrec = repobj.recgetall();
    }

    //......................................for Inserting..........................\

    public void InserRec(SignupRecord record) {
        repobj.recinsert(record);
    }
    //......................................for Inserting..........................\

    public void UpdateRec(SignupRecord record) {
        repobj.recupdate(record);
    }
    //......................................for Inserting..........................\

    public void DelRec(SignupRecord record) {
        repobj.recdelete(record);
    }

    public LiveData<List<SignupRecord>> getAllrec() {
        return allrec;
    }

    //......................................for checking Acount Exist..........................\

    public boolean Checkaccount(String name, String email) {
        return (repobj.accountcheck(name,email));

    }

    //......................................for checking Forget Acount Exist..........................\

    public boolean ForgetCheckaccount(String name, String email) {
        return (repobj.ForgeAccountCheck(name,email));

    }

    //......................................for checking Same name Exist..........................\

    public boolean SameNamechk(String name) {
        return (repobj.SameNameCheck(name));

    }

    //......................................for checking Same Email Exist..........................\

    public boolean SameEmailchk(String email) {
        return (repobj.SameEmailCheck(email));

    }


    //......................................for checking Same mbnbr Exist..........................\

    public boolean Samembnbrchk(Long mbnbr) {
        return (repobj.SamephnbrCheck(mbnbr));

    }
}
