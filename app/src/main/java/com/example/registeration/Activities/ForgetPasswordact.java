package com.example.registeration.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.registeration.R;
import com.example.registeration.RoomDB.ViewModel;

public class ForgetPasswordact extends AppCompatActivity {
    EditText txt_name,txt_email;
    Button btn_save;
    ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwordact);
        txt_name = findViewById(R.id.frgt_name);
        txt_email = findViewById(R.id.frgt_email);
        btn_save = findViewById(R.id.frgt_btsave);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
btn_save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name = txt_name.getText().toString();
        String email = txt_email.getText().toString();
        if(name.length()==0)
        {
            txt_name.setError("*Field is blanked");
            txt_name.setBackgroundResource(R.drawable.erboxshape);
        }
      else  if(email.length()==0)
        {
            txt_email.setError("*Field is blanked");
            txt_email.setBackgroundResource(R.drawable.erboxshape);
        }
       else if(!email.contains("@") || !email.contains(".com"))
        {
            txt_email.setError("*email is not in correct format");
            txt_email.setBackgroundResource(R.drawable.erboxshape);
        }
        else if(viewModel.ForgetCheckaccount(name,email))
        {
            Toast.makeText(ForgetPasswordact.this,"Check Your Email and Your password is sent",Toast.LENGTH_SHORT).show();
        }
        else             Toast.makeText(ForgetPasswordact.this,"Email and password did not matched",Toast.LENGTH_SHORT).show();

    }
});
        txt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txt_name.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txt_name.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_name.setBackgroundResource(R.drawable.boxshape);
            }
        });
        txt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txt_email.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txt_email.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_email.setBackgroundResource(R.drawable.boxshape);
            }
        });

    }
}