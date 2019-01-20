package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.ArrayList;

public class TeacherMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListFragment.OnListFragmentInteractionListener,
        View.OnClickListener, PaymentHistoryFragment.OnFragmentInteractionListener, PaymentActiveFragment.OnFragmentInteractionListener,
        PaymentOverviewFragment.OnFragmentInteractionListener {


    RecyclerView list;

   // private  static final  String TAG = "MainActivity";

    //vars
    Singleton p = Singleton.getInstance();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Pick what fragment to display oncreate
        displaySelectedScreen(R.id.nav_settings);
    }
    

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter_rating) {
            return true;
        } else if (id == R.id.action_filter_country){
            //Do stuff
        } else if (id == R.id.action_filter_price){
            //do stuff
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            displaySelectedScreen(item.getItemId());


        return true;
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        Bundle args = new Bundle();
        args.putInt("id", itemId);

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.home:
                fragment = new TeacherFragment();
//                fragment.setArguments(args);
                setTitle("Gruppe Magnus");
                break;
            case R.id.first:
                fragment = new ListFragment();
                fragment.setArguments(args);
                break;
                //Todo change nav_favorites to nav_payment.
                //THIS IS PAYMENT
            case R.id.nav_favorites:
                fragment = new PaymentOverviewFragment();
                fragment.setArguments(args);
                break;
            case R.id.nav_pending:
                fragment = new ListFragment();
                fragment.setArguments(args);
                break;
            case R.id.nav_settings:
                fragment = new TeacherFragment();
                break;
            case R.id.nav_logout:
                p.logout();
                startActivity(new Intent(this, LoginOrSignupActivity.class));
                finish();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContent, fragment);
         //   ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onListFragmentInteraction(TeacherProfile item) {
        //TODO handle clicking on profile
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
