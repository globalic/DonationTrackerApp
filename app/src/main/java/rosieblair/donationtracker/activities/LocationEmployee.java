package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.database.UserDBHelper;
import rosieblair.donationtracker.model.Location;

/**
 * SCREEN THAT LOCATION EMPLOYEES ARE DIRECTED TO AFTER SUCCESSFUL REGISTRATION ATTEMPT
 */
public class LocationEmployee extends AppCompatActivity {

    private Button select;
    private Spinner type;
    private Location loc;
    private ArrayList<Location> temp; //temporary arraylist to load locations so user can select on and assign it to them


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_employee);

        select();
    }

    public void select() {
        type = findViewById(R.id.employeeSelectLocSpinner);
        select = findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                readCSVFile();
                Log.d("locList", "" + temp);
                String locName = type.getSelectedItem().toString();
//                loc = findLocationByName(locName);
//                UserDBHelper.location.set(UserDBHelper.location.size()-1, loc.getKey());
//                Log.d("userLocs", "Location keys" + UserDBHelper.location);
//                Log.d("userLocs", "Users" + UserDBHelper.usernames);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
