package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.model.Location;
import rosieblair.donationtracker.model.User;

public class EmployeeAppScreen extends AppCompatActivity {

    private final AppCompatActivity activity = EmployeeAppScreen.this;

    private Button locationListButton;
    private Button locationInventoryButton;
    private Button addDonationButton;
    private Button searchButton;
    private String locName;

    private User user;
    private Location loc;

//    private LocationDBHelper locDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_app_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        init();
        pressSearch();
        pressAddDonation();
        pressViewLocations();
        pressViewMyLocationInventory();
    }

//    private void init() {
//
//    }

    private void pressSearch() {
        searchButton = (Button) findViewById(R.id.searchButtonE);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchScreen.class);
//                intent.putExtra("thisLocn", loc);
                startActivity(intent);
            }
        });
    }

    private void pressAddDonation() {
        addDonationButton = (Button) findViewById(R.id.addItemButton);
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AddItemScreen.class);
//                intent.putExtra("thisLocn", loc);
//                startActivity(intent);
            }
        });

    }

    private void pressViewLocations() {

    }

    private void pressViewMyLocationInventory() {
    }

    public void onLogOutPressed(View view) {

    }

}
