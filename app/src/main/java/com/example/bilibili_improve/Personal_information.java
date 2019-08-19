package com.example.bilibili_improve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Personal_information extends AppCompatActivity {

    Button mBtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        mBtest = (Button) findViewById(R.id.btn_test);

        mBtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Personal_information.this,Main_menu.class);
                startActivity(back);
                finish();
            }
        });
    }
}
