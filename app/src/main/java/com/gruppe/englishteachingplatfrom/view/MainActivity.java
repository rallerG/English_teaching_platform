package com.gruppe.englishteachingplatfrom.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListFragment.OnListFragmentInteractionListener,
        PaymentActiveFragment.OnFragmentInteractionListener, PaymentOverviewFragment.OnFragmentInteractionListener,
        PaymentHistoryFragment.OnFragmentInteractionListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    //vars
    public FloatingActionButton fab;
    public FloatingActionButton floatingActionButton;
    public int position;
    public int pic;
    Singleton p = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        p.createList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        floatingActionButton = findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null) {
            //Pick what fragment to display onCreate
            //DisplaySelectedScreen(6);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragmentContent, new frag_Pager());
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            setTitle(R.string.app_name);
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
        if (id == R.id.action_filter_rating) {
            return true;
        } else if (id == R.id.action_filter_country) {
            //Do stuff
        } else if (id == R.id.action_filter_price) {
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
            case R.id.nav_home:
                fragment = new frag_Pager();;
                fragment.setArguments(args);
                setTitle(R.string.app_name);
                break;
            case R.id.nav_matches:
                fragment = new ListFragment();
                fragment.setArguments(args);
                setTitle("Matches");
                break;
            case R.id.nav_favorites:
                fragment = new ListFragment();
                fragment.setArguments(args);
                setTitle("Favorites");
                break;
            case R.id.nav_pending:
                fragment = new ListFragment();
                fragment.setArguments(args);
                setTitle("Pending");
                break;
            case R.id.nav_money:
                fragment = new PaymentOverviewFragment();
                fragment.setArguments(args);
                setTitle("Payment");
                break;
            case R.id.nav_settings:
                //  fragment = new frag_Pager();
                fragment = new frag_Pager();
                fragment.setArguments(args);
                setTitle("Settings");
                break;
            case R.id.nav_logout:
                startActivity(new Intent(this, LoginOrSignupActivity.class));
                finish();
                break;
            default:
                fragment = new frag_Pager();
                fragment.setArguments(args);
                setTitle(R.string.app_name);
                break;
        }

        //replacing the fragment
        if (fragment != null ) {
            getSupportFragmentManager().popBackStack();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContent, fragment);
            ft.addToBackStack(null);
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
    public void onClick(View view) {
        if (view == fab) {
            Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragmentContent);

            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putInt("pic", pic);
            Fragment F = new DialogBox();
            F.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, F).
                    addToBackStack(null).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
