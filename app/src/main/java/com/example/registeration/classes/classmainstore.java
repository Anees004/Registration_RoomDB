package com.example.registeration.classes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.registeration.RoomDB.DAO;
import com.example.registeration.RoomDB.DBuildupRecords;
import com.example.registeration.RoomDB.SignupRecord;

import java.util.List;

public class classmainstore {

    private DAO Dao;

    public classmainstore(Application application) {
       DBuildupRecords dBuildupRecords = DBuildupRecords.getInstance(application);
       Dao = dBuildupRecords.dao();
        allrec = new MutableLiveData<>();
        allrec = Dao.getalldata();
    }

    private LiveData<List <SignupRecord>> allrec;

    public void recinsert(SignupRecord record)
    {
new InserAsyncTask(Dao).execute(record);
    }

    public void recdelete(SignupRecord record)
    {
new DeleteAsyncTask(Dao).execute(record);
    }

    public boolean accountcheck(String name, String email)
    {
        AccountCheck ac=new AccountCheck(Dao);
        ac.execute(name,email);
        return ac.status;
    }

    public boolean ForgeAccountCheck(String name, String email)
    {
        ForgetAccountCheck ac=new ForgetAccountCheck(Dao);
        ac.execute(name,email);
        return ac.status;
    }

    public boolean SameNameCheck(String name)
    {
        SameNameCheck ac=new SameNameCheck(Dao);
        ac.execute(name);
        return ac.status;
    }

    public boolean SameEmailCheck(String email)
    {
        SameEmailCheck ac=new SameEmailCheck(Dao);
        ac.execute(email);
        return ac.status;
    }

    public boolean SamephnbrCheck(Long nbr)
    {
        SamembnbrCheck ac=new SamembnbrCheck(Dao);
        ac.execute(nbr);
        return ac.status;
    }

    public void recupdate(SignupRecord record)
    {
new UpdateAsyncTask(Dao).execute(record);
    }

    public LiveData<List <SignupRecord>> recgetall()
    {
        return allrec;
    }


    //++++++++++++++++++++++++++++class for Insertion+++++++++++++++++++++++++++++++++

    private static class InserAsyncTask extends AsyncTask<SignupRecord,Void,Void>
    {
        public InserAsyncTask(DAO dao) {
            Dao = dao;
        }

        private DAO Dao;

        @Override
        protected Void doInBackground(SignupRecord... signupRecords) {
           Dao.Singupinsertion(signupRecords[0]);
            return null;
        }
    }


    //++++++++++++++++++++++++++++class for Update+++++++++++++++++++++++++++++++++

    private static class UpdateAsyncTask extends AsyncTask<SignupRecord,Void,Void>
    {
        public UpdateAsyncTask(DAO dao) {
           this.Dao = dao;
        }

        private DAO Dao;

        @Override
        protected Void doInBackground(SignupRecord... signupRecords) {
            Dao.dbupdate(signupRecords[0]);
            return null;
        }
    }


    //++++++++++++++++++++++++++++class for Delete+++++++++++++++++++++++++++++++++

    private static class DeleteAsyncTask extends AsyncTask<SignupRecord,Void,Void>
    {
        public DeleteAsyncTask(DAO dao) {
            Dao = dao;
        }

        private DAO Dao;

        @Override
        protected Void doInBackground(SignupRecord... signupRecords) {
            Dao.Signupdelete(signupRecords[0]);
            return null;
        }
    }
    //++++++++++++++++++++++++++++class for Account check+++++++++++++++++++++++++++++++++
    private static class AccountCheck extends AsyncTask<String,Void, Boolean>
    {
        private final DAO Dao;
        public static Boolean status = false;

        public AccountCheck(DAO dao) {
            this.Dao = dao;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
        String myString = strings[0];
        String email=strings[1];

            return  Dao.CheckAccount(myString ,email );
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            status = aBoolean;
        }
    }

    //++++++++++++++++++++++++++++class for forgetaccount check+++++++++++++++++++++++++++++++++
    private static class ForgetAccountCheck extends AsyncTask<String,Void, Boolean>
    {
        private final DAO Dao;
        public static Boolean status = false;

        public ForgetAccountCheck(DAO dao) {
            this.Dao = dao;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String myString = strings[0];
            String email=strings[1];

            return  Dao.CheckAccount(myString ,email );
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            status = aBoolean;
        }
    }

    //++++++++++++++++++++++++++++class for same name check+++++++++++++++++++++++++++++++++
    private static class SameNameCheck extends AsyncTask<String,Void, Boolean>
    {
        private final DAO Dao;
        public static Boolean status = false;

        public SameNameCheck(DAO dao) {
            this.Dao = dao;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String myString = strings[0];

            return  Dao.dbsamename(myString );
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            status = aBoolean;
        }
    }

    //++++++++++++++++++++++++++++class for sameemail check+++++++++++++++++++++++++++++++++
    private static class SameEmailCheck extends AsyncTask<String,Void, Boolean>
    {
        private final DAO Dao;
        public static Boolean status = false;

        public SameEmailCheck(DAO dao) {
            this.Dao = dao;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String email=strings[0];

            return  Dao.dbsameemail(email );
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            status = aBoolean;
        }
    }

    //++++++++++++++++++++++++++++class for forgetaccount check+++++++++++++++++++++++++++++++++
    private static class SamembnbrCheck extends AsyncTask<Long,Void, Boolean>
    {
        private final DAO Dao;
        public static Boolean status = false;

        public SamembnbrCheck(DAO dao) {
            this.Dao = dao;
        }


        @Override
        protected Boolean doInBackground(Long... longs) {
            Long mbnbr = longs[0];
            return  Dao.dbsamemobile(mbnbr);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            status = aBoolean;
        }
    }
}