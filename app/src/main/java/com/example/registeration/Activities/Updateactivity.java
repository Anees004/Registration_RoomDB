package com.example.registeration.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.registeration.R;
import com.example.registeration.RoomDB.SignupRecord;
import com.example.registeration.RoomDB.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Updateactivity extends AppCompatActivity {
    TextView txtid, txtname, txtemail, txtpaswrd, txtmbnbr;
    Button btnsave;
    String name, emailid, password;

    List<SignupRecord> itemlist = new ArrayList<>();
    long mb_Nbr;
    int position, id;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);
        txtid = findViewById(R.id.upd_txtid);
        txtemail = findViewById(R.id.upd_txtemail);
        txtname = findViewById(R.id.upd_txtname);
        txtmbnbr = findViewById(R.id.upd_mbnbr);
        txtpaswrd = findViewById(R.id.upd_passwrdd);
        btnsave = findViewById(R.id.upd_btnsave);

        final SignupRecord signupRecord = (SignupRecord) getIntent().getSerializableExtra("signupRecord");
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        position = getIntent().getIntExtra("position", 0);
        itemlist = (List<SignupRecord>) getIntent().getSerializableExtra("listitm");
        txtid.setText(String.valueOf(itemlist.get(position).getSignupid()));
        txtname.setText(itemlist.get(position).getUsername());
        txtemail.setText(itemlist.get(position).getUseremail());
        txtmbnbr.setText(String.valueOf(itemlist.get(position).getUsermobile_nbr()));
        txtpaswrd.setText(itemlist.get(position).getUserpassword());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storein_var();
                if (name.length() != 0 && password.length() != 0 && txtmbnbr.length() != 0 && emailid.contains("@") && emailid.contains(".com")) {
                    storein_var();
                    SignupRecord signuprec = new SignupRecord(id,name,emailid,password,mb_Nbr);
                    viewModel.UpdateRec(signuprec);
                      finish();
                    //startActivity(new Intent(Updateactivity.this, AllRecordact.class));

                } else {
                }
                if (name.length() == 0) {
                    txtname.setError("*Name field is empty");
                    txtname.setBackgroundResource(R.drawable.erboxshape);
                } else if (password.length() == 0) {
                    txtpaswrd.setError("*password field is empty");
                    txtpaswrd.setBackgroundResource(R.drawable.erboxshape);
                } else if (!emailid.contains("@") || !emailid.contains(".com")) {
                    txtemail.setError("*email is not correct");
                    txtemail.setBackgroundResource(R.drawable.erboxshape);
                } else if (txtmbnbr.length() == 0) {
                    txtmbnbr.setError("*mobile number is not correct");
                    txtmbnbr.setBackgroundResource(R.drawable.erboxshape);
                } else if (password.length() == 0) {
                    txtpaswrd.setError("*Password is not correct");
                    txtpaswrd.setBackgroundResource(R.drawable.erboxshape);
                }

            }
        });
        txtname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtname.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtname.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtname.setBackgroundResource(R.drawable.boxshape);
            }
        });
        txtemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtemail.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtemail.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtemail.setBackgroundResource(R.drawable.boxshape);
            }
        });

        txtmbnbr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtmbnbr.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtmbnbr.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtmbnbr.setBackgroundResource(R.drawable.boxshape);
            }
        });

        txtpaswrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtpaswrd.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtpaswrd.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtpaswrd.setBackgroundResource(R.drawable.boxshape);
            }
        });

    }

    public void loadsignup(SignupRecord signupRecord) {
        {
            txtname.setText(signupRecord.getUsername());
            txtemail.setText(signupRecord.getUseremail());
            txtpaswrd.setText(signupRecord.getUserpassword());
            String mb = String.valueOf(signupRecord.getUsermobile_nbr());
            txtmbnbr.setText(mb);
        }
    }


    private void storein_var() {
        id = Integer.parseInt(txtid.getText().toString());
        name = txtname.getText().toString();
        emailid = txtemail.getText().toString();
        password = txtpaswrd.getText().toString();
        mb_Nbr = Long.parseLong(txtmbnbr.getText().toString());
    }

}