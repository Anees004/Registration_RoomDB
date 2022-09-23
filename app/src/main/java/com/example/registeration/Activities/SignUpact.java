package com.example.registeration.Activities;

import android.content.Intent;
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
import com.example.registeration.RoomDB.SignupRecord;
import com.example.registeration.RoomDB.ViewModel;

public class SignUpact extends AppCompatActivity {
    ViewModel viewModel;
    EditText fullname, email, first_pswrd, confirm_pswrd, mb_nbr;
    Button btnsignup;
    String name, emailid, f_password, c_password;
    long mb_Nbr;

SignupRecord signupRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upact);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        fullname = (EditText) findViewById(R.id.suptxt_name);
        email = findViewById(R.id.suptxt_email);
        first_pswrd = findViewById(R.id.suptxt_fpswrd);
        confirm_pswrd = findViewById(R.id.suptxt_cpswrd);
        mb_nbr = findViewById(R.id.suptxt_phno);
        btnsignup = findViewById(R.id.sup_btnsignup);
        initialize_edttxt();

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storein_var();
                if (name.equals("") && emailid.equals("") && f_password.equals("") && c_password.equals("") && mb_nbr.equals("")) {
                    showtoast("Nothing is written!");
                    fullname.setBackgroundResource(R.drawable.erboxshape);
                    fullname.setError("*must write username");
                    email.setBackgroundResource(R.drawable.erboxshape);
                    email.setError("*must write email");
                    first_pswrd.setBackgroundResource(R.drawable.erboxshape);
                    first_pswrd.setError("*must write password");
                    confirm_pswrd.setBackgroundResource(R.drawable.erboxshape);
                    confirm_pswrd.setError("*must write matching password");
                    mb_nbr.setBackgroundResource(R.drawable.erboxshape);
                    mb_nbr.setError("*must write mobile number");

                } else {
                    if (f_password.equals(c_password) && emailid.contains("@") && emailid.contains(".com") && name.length() >= 4 &&
                            c_password.length() > 4 && mb_nbr.length() == 11 && !viewModel.SameEmailchk(emailid)
                            &&!viewModel.SameNamechk(name) && !viewModel.Samembnbrchk(mb_Nbr)) {
                        signupRecord = new SignupRecord(name,emailid,c_password,mb_Nbr);
                        viewModel.InserRec(signupRecord);
                        showtoast("Registered Successfully"+ viewModel.SameNamechk(name) +name);
                        initialize_edttxt();
                        finish();
                        startActivity(new Intent(SignUpact.this, MainActivity.class));
                    } else if (name.length() < 4) {
                        fullname.setError("*at least 4 chars needed");
                        fullname.setBackgroundResource(R.drawable.erboxshape);
                    } else if (!emailid.contains("@") || !emailid.contains(".com")) {
                        email.setError("*email is incorrect");
                        email.setBackgroundResource(R.drawable.erboxshape);
                    } else if (mb_nbr.length() != 11) {
                        mb_nbr.setError("*mobile nbr is wrong");
                        mb_nbr.setBackgroundResource(R.drawable.erboxshape);
                    } else if (f_password.length() <= 4) {
                        first_pswrd.setError("*password is too short");
                        first_pswrd.setBackgroundResource(R.drawable.erboxshape);
                    } else if (!f_password.equals(c_password)) {
                        confirm_pswrd.setError("*must match the first password");
                        confirm_pswrd.setBackgroundResource(R.drawable.erboxshape);
                    }
                    else if(viewModel.SameNamechk(name))
                    {
                        viewModel.SameNamechk(name);
                        fullname.setError(name + " *exist before");
                        fullname.setBackgroundResource(R.drawable.erboxshape);
                    }
                    else if(viewModel.SameEmailchk(emailid))
                    {
                        viewModel.SameEmailchk(emailid);
                        email.setError(emailid + " *exist before");
                        email.setBackgroundResource(R.drawable.erboxshape);
                    }
                    else if(viewModel.Samembnbrchk(mb_Nbr))
                    {
                        viewModel.Samembnbrchk(mb_Nbr);
                        mb_nbr.setError(mb_Nbr + " *exist before");
                        mb_nbr.setBackgroundResource(R.drawable.erboxshape);
                    }
                }
            }

        });




        //------------------To see the as the error of the edit text is now correct or not--------
        fullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fullname.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullname.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                fullname.setBackgroundResource(R.drawable.boxshape);
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                email.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                email.setBackgroundResource(R.drawable.boxshape);
            }
        });

        mb_nbr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mb_nbr.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mb_nbr.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mb_nbr.setBackgroundResource(R.drawable.boxshape);
            }
        });

        first_pswrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                first_pswrd.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                first_pswrd.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                first_pswrd.setBackgroundResource(R.drawable.boxshape);
            }
        });

        confirm_pswrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirm_pswrd.setBackgroundResource(R.drawable.erboxshape);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirm_pswrd.setBackgroundResource(R.drawable.boxshape);
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirm_pswrd.setBackgroundResource(R.drawable.boxshape);
            }
        });

    }

    private void initialize_edttxt() {
        fullname.setText("");
        email.setText("");
        first_pswrd.setText("");
        confirm_pswrd.setText("");
        mb_nbr.setText("");
    }

    private void storein_var() {
        name = fullname.getText().toString();
        emailid = email.getText().toString();
        f_password = first_pswrd.getText().toString();
        c_password = confirm_pswrd.getText().toString();
        if(mb_nbr.getText().toString().length()>1)
            mb_Nbr = Long.parseLong(mb_nbr.getText().toString());
        else
        mb_Nbr = 0;

    }
    private void showtoast(String txt) {
        Toast toast = Toast.makeText(getApplicationContext(),
                txt,
                Toast.LENGTH_SHORT);
        toast.show();
    }

}