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
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText mTUsername;
    EditText mTPassword;
    Button mBLogin;
    TextView mTVRegister;
    Database db;
    Switch mSwitch;

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

        SharedPreferences sharedPreferences=getSharedPreferences("config",0);
        if (sharedPreferences.getString("login",null) != null){
            Intent asd = new Intent(Login.this,Personal_information.class);
            startActivity(asd);
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
                    //把密码和用户名存起来
                    //getSharedPreferences(name,model);,name 会生成一个xml文件，model ：模式，可读可写等模式
                    //注意在做logout时将其改为null
                    SharedPreferences sp = getSharedPreferences("config", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("login", "1");
                    //提交数据
                    editor.commit();

                    finish();//kill the page
                    Intent success_loginIntent = new Intent(Login.this,Main_menu.class);
                    //Intent success_loginIntent = new Intent(Login.this,Homepage.class);
                    startActivity(success_loginIntent);
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

