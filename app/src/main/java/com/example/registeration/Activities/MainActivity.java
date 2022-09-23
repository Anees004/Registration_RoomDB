package com.example.registeration.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.registeration.R;
import com.example.registeration.RoomDB.DBuildupRecords;
import com.example.registeration.RoomDB.ViewModel;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    TextView forgetpswrd;
    Button btnsignup, btnsignin, btnadmin;
    String Name, Password;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txt_name);
        password = findViewById(R.id.txt_password);
        forgetpswrd = findViewById(R.id.txtforget);
        btnsignin = findViewById(R.id.btnsignin);
        btnsignup = findViewById(R.id.btnsignup);
        btnadmin = findViewById(R.id.btnadmin);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        initialize_edttxt();      
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store_data();
                signinclick();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(MainActivity.this, SignUpact.class);
                startActivity(intent_one);
            }
        });
        forgetpswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetPasswordact.class);
                startActivity(intent);
            }
        });
        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllRecordact.class);
                startActivity(intent);
            }
        });
        //...........for monitering tbe changings in textboxes
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                name.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                name.setBackgroundResource(R.drawable.boxshape);
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                password.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                password.setBackgroundResource(R.drawable.boxshape);
            }
        });

    }

    private void initialize_edttxt() {
        name.setText("");
        password.setText("");
    }

    private void store_data() {
        Name = name.getText().toString().trim();
        Password = password.getText().toString().trim();
    }

    private void showtoast(String txt) {
        Toast toast = Toast.makeText(getApplicationContext(),
                txt,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    private void signinclick() {
        DBuildupRecords gnin = Room.databaseBuilder(MainActivity.this, DBuildupRecords.class, "Users").allowMainThreadQueries().build();
        if (Name.equals("")) {
            name.setError("username is needed*");
            name.setBackgroundResource(R.drawable.erboxshape);
        }
        if (Password.equals("")) {
            password.setError("password is needed*");
            password.setBackgroundResource(R.drawable.erboxshape);
        } else {

            if (viewModel.Checkaccount(Name, Password)) {
                Intent intent_two = new Intent(MainActivity.this, FinalPage.class);
                intent_two.putExtra("name", Name);
                startActivity(intent_two);
                initialize_edttxt();
                btnadmin.setVisibility(View.INVISIBLE);
            } else if (Name.equals("Registration Admin") && Password.equals("Registration Admin"))
                btnadmin.setVisibility(View.VISIBLE);
            else {
                showtoast("unmatched Email & Password");
            }
        }
    }
}