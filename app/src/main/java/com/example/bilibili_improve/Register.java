package com.example.bilibili_improve;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText mTUsername;
    EditText mTPassword;
    EditText mTCheckPassword;
    Button mBRegister;
    TextView mTVLogin;
    Database db;
    Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppCompatAcitiviy:getSupportActionBar().hide();

        db = new Database(this);
        mTUsername = (EditText) findViewById(R.id.text_username);
        mTPassword = (EditText) findViewById(R.id.text_password);
        mTCheckPassword = (EditText) findViewById(R.id.text_password_check);
        mBRegister = (Button) findViewById(R.id.btn_register);
        mTVLogin = (TextView) findViewById(R.id.textview_login);
        mSwitch = (Switch) findViewById(R.id.show_password);
        mTVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Register.this,Login.class);
                startActivity(LoginIntent);
            }
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    mTPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mTCheckPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{

                    mTPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mTCheckPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        mBRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTUsername.getText().toString().trim();
                String pwd = mTPassword.getText().toString().trim();
                String check_pwd = mTCheckPassword.getText().toString().trim();
                Boolean res = db.check_repeatUser(user);
                if (pwd.equals(check_pwd)) {
                    if (res == false) {
                        long val = db.addUser(user, pwd);
                        if (val > 0) {
                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(Register.this, Login.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(Register.this, "Registeration ERROR", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "The Username has been already regitered", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
