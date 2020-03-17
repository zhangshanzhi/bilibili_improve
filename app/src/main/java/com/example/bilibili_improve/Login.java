package com.example.bilibili_improve;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText mTUsername;
    EditText mTPassword;
    Button mBLogin;
    TextView mTVRegister;
    Database db;
    Switch mSwitch;
    Context c;
    public static final String PREFERENCE_PACKAGE = "com.example.bilibili_improve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCompatAcitiviy:getSupportActionBar().hide();
        db = new Database(this);
        mTUsername = (EditText) findViewById(R.id.text_username);
        mTPassword = (EditText) findViewById(R.id.text_password);
        mBLogin = (Button) findViewById(R.id.btn_login);
        mTVRegister = (TextView) findViewById(R.id.textview_reg);
        mSwitch = (Switch) findViewById(R.id.show_password);

        try {
            c = this.createPackageContext(PREFERENCE_PACKAGE, Context.CONTEXT_IGNORE_SECURITY);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    mTPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{

                    mTPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
//wasdoj
        mTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
            }
        });
        mBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTUsername.getText().toString().trim();
                String pwd = mTPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);

                if (res == true){
                    Toast.makeText(Login.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
                    Intent success_loginIntent = new Intent(Login.this,Main_menu.class);

                    SharedPreferences.Editor editor = c.getSharedPreferences("config", 0).edit();
                    editor.putString("login", "1");
                    editor.putString("user",user);
                    editor.commit();
                    startActivity(success_loginIntent);
                    finish();//kill the page
                }
                else
                    Toast.makeText(Login.this,"Login Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

//新方法改变密码显示
//TransformationMethod type = mTPassword.getTransformationMethod();
                    /*if (PasswordTransformationMethod.getInstance().equals(type)){
                        mTPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }else {
                        mTPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }*/

