package com.example.registeration.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registeration.R;

public class FinalPage extends AppCompatActivity {
    TextView sh_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        sh_name = findViewById(R.id.txtout_name);
       sh_name.setText(getIntent().getStringExtra("name")+"!");
    }
}