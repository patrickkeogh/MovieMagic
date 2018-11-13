package com.programming.kantech.moviemagic.app.views.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.programming.kantech.moviemagic.app.R;
import com.programming.kantech.moviemagic.app.utils.Constants;
import com.programming.kantech.moviemagic.app.views.fragments.Fragment_Main;


import java.util.Objects;

public class Activity_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentManager mFragmentManager;

    private boolean mIsFirstTimeLoaded = true;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        //FrameLayout container_master = findViewById(R.id.container_master);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null && mIsFirstTimeLoaded) {
            // add main fragment to master container on first start
            mIsFirstTimeLoaded = false;
            Fragment_Main fragment = Fragment_Main.newInstance();
            replaceFragment(R.id.container_master, Constants.TAG_FRAGMENT_MAIN_START, fragment, true);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void replaceFragment(int container, String fragment_tag,
                                 Fragment fragment_in, boolean addToBackStack) {

        // Get a fragment transaction to replace fragments
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        //invalidateOptionsMenu();

        if (Objects.equals(fragment_tag, Constants.TAG_FRAGMENT_MAIN_START)) {
            transaction.add(container, fragment_in, fragment_tag);
            if (addToBackStack) transaction.addToBackStack(null);
        } else {
            transaction.replace(container, fragment_in, fragment_tag);
            if (addToBackStack) transaction.addToBackStack(null);
        }

        // Commit the transaction
        transaction.commit();

    }
}
