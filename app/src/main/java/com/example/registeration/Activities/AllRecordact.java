package com.example.registeration.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registeration.R;
import com.example.registeration.RoomDB.SignupRecord;
import com.example.registeration.RoomDB.ViewModel;
import com.example.registeration.classes.RecycleAdapter;

import java.util.List;

public class AllRecordact extends AppCompatActivity {

    ViewModel viewModel;
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recordact);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        adapter = new RecycleAdapter(AllRecordact.this,viewModel);
        recyclerView.setAdapter(adapter);
        viewModel.getAllrec().observe(this, new Observer<List<SignupRecord>>() {
            @Override
            public void onChanged(List<SignupRecord> recordList) {
                adapter.setRec(recordList);
            }
        });

    }

    public void INSETREC (SignupRecord obj)
    {
        viewModel.InserRec(obj);
    }


    public void UPDATEREC (SignupRecord obj)
    {
        viewModel.InserRec(obj);
    }
}