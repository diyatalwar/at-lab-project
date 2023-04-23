package com.example.sahaayak_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class SettingsScreen extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setNavigationDrawer(); // call method

        // initialising list
        ListView settings = (ListView) findViewById(R.id.settingsList);
        String[] listItems = new String[]{"Update E-mail Address", "Change Password", "Contact Your Administrator", "Logout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
        settings.setAdapter(adapter);

        settings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemValue = (String) settings.getItemAtPosition(i);
                switch(itemValue){
                    case "Change Password":
                        // add code for password change screen (intent)
                        break;

                    case "Update E-mail Address":
                        // add code for email change screen (intent)
                        break;

                    case "Contact Your Administrator":
                        // call associated admin (get phone number from database)
                        break;

                    case "Logout":
                        Intent login = new Intent(SettingsScreen.this, MainActivity.class);
                        startActivity(login);
                }
            }
        });

    }

    private void setNavigationDrawer() {

        DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawer_layout); // initiate a DrawerLayout
        NavigationView navView = (NavigationView) findViewById(R.id.navigation); // initiate a Navigation View
// implement setNavigationItemSelectedListener event on NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId(); // get selected menu item's id
                if (itemId == R.id.nav_dashboard) {
                    Intent i1 = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(i1);
                } else if (itemId == R.id.nav_settings) {
                    Intent i2 = new Intent(getApplicationContext(), SettingsScreen.class);
                    startActivity(i2);
                } else if(itemId == R.id.nav_resources){
                    Intent i3 = new Intent(getApplicationContext(), ResourcesScreen.class);
                    startActivity(i3);
                } else if(itemId==R.id.nav_badges){

                } else if (itemId==R.id.nav_jobs){

                } else if(itemId==R.id.nav_tracker){

                }
                return false;
            }
        });
    }


    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}