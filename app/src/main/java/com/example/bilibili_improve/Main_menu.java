package com.example.bilibili_improve;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button mBuserhead;
    Button mBsearch;
    Button mBgame;
    Button mBdownload;
    Button mBemail;
    Button mset;
    DrawerLayout mDrawer;
    SharedPreferences msharedPreferences;
    public static Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBuserhead =(Button) findViewById(R.id.userhead);
        mBsearch =(Button) findViewById(R.id.search_method);
        mBgame =(Button) findViewById(R.id.game_center);
        mBemail =(Button) findViewById(R.id.eamil);
        mBdownload =(Button) findViewById(R.id.download);
        mset = (Button) findViewById(R.id.app_set);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        msharedPreferences= (SharedPreferences)getSharedPreferences("config",0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //系统版本大于19
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.normal_pink);//设置标题栏颜色，此颜色在color中声明

        mBsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_menu.this,"mBsearch",Toast.LENGTH_SHORT).show();
            }
        });

        mBgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_menu.this,"mBgame",Toast.LENGTH_SHORT).show();
            }
        });

        mBemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_menu.this,"mBemail",Toast.LENGTH_SHORT).show();
            }
        });

        mBdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_menu.this,"mBdownload",Toast.LENGTH_SHORT).show();
            }
        });

        mset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout_intent = new Intent(Main_menu.this, Logout.class);
                startActivity(logout_intent);
            }
        });

        //隐藏title
        AppCompatAcitiviy:getSupportActionBar().hide();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new bookstoreFragment()).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        Button user_information = (Button) headerView.findViewById(R.id.user_self_login);
        final TextView un = (TextView) headerView.findViewById(R.id.un);

        if (msharedPreferences.getString("user",null) != null){
            un.setText("user name: "+ msharedPreferences.getString("user",null));
        }

        user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msharedPreferences.getString("login",null).equals("1")){
                    //Toast.makeText(Main_menu.this,msharedPreferences.getString("login",null),Toast.LENGTH_SHORT).show();
                    Intent asd = new Intent(Main_menu.this,Personal_information.class);
                    startActivity(asd);
                }
                else {
                    Intent LoginIntent = new Intent(Main_menu.this, Login.class);
                    startActivity(LoginIntent);
                    finish();
                }
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mBuserhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                un.setText("user name :");
            }
        };
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_bookstore:
                    selectedFragment = new bookstoreFragment();
                    break;
                case R.id.nav_me:
                    selectedFragment = new MeFragment();
                    break;
                case R.id.nav_friend:
                    selectedFragment = new FriendFragment();
                    break;
                case R.id.nav_something:
                    selectedFragment = new somethingFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }

    };

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;        // a|=b的意思就是把a和b按位或然后赋值给a   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
        } else {
            winParams.flags &= ~bits;        //&是位运算里面，与运算  a&=b相当于 a = a&b  ~非运算符
        }
        win.setAttributes(winParams);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_record) {

        } else if (id == R.id.nav_download) {

        } else if (id == R.id.nav_likes) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_laterwatch) {

        }else if (id == R.id.nav_hotactivity) {

        }else if (id == R.id.nav_live) {

        }else if (id == R.id.nav_achievement) {

        }else if (id == R.id.nav_non_data) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
