package com.example.registeration.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 *
 */
//This class is our Db
@Database(entities = SignupRecord.class, version = 1)
public abstract class DBuildupRecords extends RoomDatabase {


    public static DBuildupRecords instance;
    public abstract DAO dao();

    public static synchronized DBuildupRecords getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context,DBuildupRecords.class,"signup_records").
                    fallbackToDestructiveMigration().addCallback(roomcallback).build();

        }
        return  instance;
    }

    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback()
    {


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
new populatedbAsyn(instance).execute();
        }
    };

    private static class populatedbAsyn extends AsyncTask<Void,Void,Void>
    {
        public populatedbAsyn(DBuildupRecords db) {
            this.dao = db.dao();
        }

        private DAO dao;
        @Override
        protected Void doInBackground(Void... voids) {
            dao.Singupinsertion(new SignupRecord("name","email","passswo",311));
            dao.Singupinsertion(new SignupRecord("name2","email2","passswo2",03117));
            dao.Singupinsertion(new SignupRecord("name3","email3","passswo3",031157));
            return null;
        }
    }
}
