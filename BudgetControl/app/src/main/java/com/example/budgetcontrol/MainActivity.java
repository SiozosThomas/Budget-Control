package com.example.budgetcontrol;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static FragmentManager fragmentManager;
    public static UserDatabase userDatabase;
    public static OutcomeDatabase outcomeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "userInfo").allowMainThreadQueries().build();
        outcomeDatabase = Room.databaseBuilder(getApplicationContext(), OutcomeDatabase.class, "outcome").allowMainThreadQueries().build();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return;
            if ( firstStart ) {
                System.out.println("Aaaa");
                showStartFragment();
            } else {
                fragmentManager.beginTransaction().add(R.id.fragment_container, new Balance()).commit();
            }
        }

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account_balance:
                        menuItem.setChecked(true);
                        displayMessage("Account Balance Selected...");
                        drawerLayout.closeDrawers();
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new Balance()).commit();
                        break;
                    case R.id.outcome_categories:
                        menuItem.setChecked(true);
                        displayMessage("Outcome Categories Selected...");
                        drawerLayout.closeDrawers();
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new Categories()).commit();
                        break;
                    case R.id.baselines:
                        menuItem.setChecked(true);
                        displayMessage("Graphs Selected...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.set_limit:
                        menuItem.setChecked(true);
                        displayMessage("Set Limit Selected...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.outcome_warning:
                        menuItem.setChecked(true);
                        displayMessage("Outcome Warning Selected...");
                        drawerLayout.closeDrawers();
                        return true;

                }
                return true;
            }
        });
    }

    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showStartFragment() {
        fragmentManager.beginTransaction().add(R.id.fragment_container, new AddUserInfo()).commit();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

}
