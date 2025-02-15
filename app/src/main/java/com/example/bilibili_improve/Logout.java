package com.example.bilibili_improve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class Logout extends AppCompatActivity {

    Button mlogout;
    Context c;
    public static final String PREFERENCE_PACKAGE = "com.example.bilibili_improve";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        try {
            c = this.createPackageContext(PREFERENCE_PACKAGE, Context.CONTEXT_IGNORE_SECURITY);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        handler = Main_menu.handler;

        mlogout = (Button) findViewById(R.id.btn_logout);

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = c.getSharedPreferences("config", 0).edit();
                editor.putString("login", "0");
                editor.putString("user",null);
                Message message=new Message();
                message.what=100;
                handler.sendMessage(message);
                editor.commit();

            }
        });

    }
}
