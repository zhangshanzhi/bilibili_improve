package com.example.bilibili_improve;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button mBuserhead;
    Button mBsearch;
    Button mBgame;
    Button mBdownload;
    Button mBemail;
    DrawerLayout mDrawer;


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
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        //隐藏title
        AppCompatAcitiviy:getSupportActionBar().hide();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new bookstoreFragment()).commit();


        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        Button user_information = (Button) headerView.findViewById(R.id.user_self_login);

        user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Main_menu.this,Login.class);
                startActivity(LoginIntent);
                finish();
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
