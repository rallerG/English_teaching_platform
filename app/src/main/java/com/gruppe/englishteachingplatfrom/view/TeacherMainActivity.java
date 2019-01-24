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
import android.widget.ImageView;
import android.widget.TextView;


import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.text.BreakIterator;
import java.util.ArrayList;

public class TeacherMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListFragment.OnListFragmentInteractionListener,
        View.OnClickListener, PaymentHistoryFragment.OnFragmentInteractionListener, PaymentActiveFragment.OnFragmentInteractionListener,
        PaymentOverviewFragment.OnFragmentInteractionListener, TeacherProfilePageFragment.OnFragmentInteractionListener {


    RecyclerView list;

   // private  static final  String TAG = "MainActivity";

    //vars
    Singleton p = Singleton.getInstance();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    NavigationView navigationView;
    View headerView;
    TextView burgerMenuName;
    ImageView profilePicture;

    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_teacher);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_teacher);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view_teacher);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);
        burgerMenuName = headerView.findViewById(R.id.teacher_name_nav);
        profilePicture = headerView.findViewById(R.id.navBarProfilePicture);
        burgerMenuName.setText(p.getCurrrentTeacher().getName());
        profilePicture.setImageResource(p.getCurrrentTeacher().getProfilePic());

        //Pick what fragment to display oncreate
        displaySelectedScreen(R.id.home);
    }
    

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_teacher);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (item != null) {
            item.setChecked(false);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        this.item = item;
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
                fragment.setArguments(args);
                setTitle("Gruppe Magnus");
                break;
            case R.id.yourStudents:
                fragment = new ListFragment();
                fragment.setArguments(args);
                setTitle("Your students");
                break;
            case R.id.yourPayments:
                fragment = new PaymentOverviewFragment();
                fragment.setArguments(args);
                setTitle("Payment");
                break;
            case R.id.nav_account:
                fragment = new TeacherProfilePageFragment();
                setTitle("Account");
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_teacher);
        drawer.closeDrawer(GravityCompat.START);
        if (p.getCurrrentTeacher() != null){
            burgerMenuName.setText(p.getCurrrentTeacher().getName());
        }
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
