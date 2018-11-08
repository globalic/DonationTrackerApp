package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.database.UserDBHelper;
import rosieblair.donationtracker.model.Location;
import rosieblair.donationtracker.model.User;

/**
 * SCREEN THAT LOCATION EMPLOYEES ARE DIRECTED TO AFTER SUCCESSFUL REGISTRATION ATTEMPT
 */
public class LocationEmployee extends AppCompatActivity {

    private final AppCompatActivity activity = LocationEmployee.this;

    private Spinner locSpinner;
    private Location loc;
    private ArrayList<Location> temp; //temporary arraylist to load locations so user can select on and assign it to them

    private UserDBHelper dbhelper;
    private LocationDBHelper loc_dbhelper;
    private String username;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_employee);

        loc_dbhelper = new LocationDBHelper(activity);
        dbhelper = new UserDBHelper(activity);
        Intent intent = getIntent();
        username = intent.getStringExtra("empUsername");

        check();
        spinner();
        select();

    }

    private void check() {
        if (username == null) {
            Log.d("registration", "ERROR: intent string not passed into LocationEmployee");
            finish();
        }
        user = dbhelper.findUserByUsername(username);
        if (user == null) {
            Log.d("locationEmployee", "ERROR: user with username not found in DB" + username);
            finish();
        }
    }

    private void spinner() {
        locSpinner = findViewById(R.id.employeeSelectLocSpinner);
        List<Location> locList = loc_dbhelper.locationList();
        String[] spinEntries = new String[locList.size()];
        for (int i = 0; i < locList.size(); i++) {
            spinEntries[i] = locList.get(i).getName();
        }

        ArrayAdapter<String> spinAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinEntries);
        spinAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(spinAdapt);

    }

    private void select() {
        Log.d("locationEmployee", "SUCCESS: user with username found in DB" + username);
        Button selectButton = findViewById(R.id.select);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int selected = locSpinner.getSelectedItemPosition() + 1;
                loc = loc_dbhelper.getLocationByName(locSpinner.getSelectedItem().toString());
                user.setEmpId(loc.getKey());
                Log.d("LOCATIONEMPLOYEE", "user empId: " + loc.getKey());
//                loc = findLocationByName(locName);
//                UserDBHelper.location.set(UserDBHelper.location.size()-1, loc.getKey());
//                Log.d("userLocs", "Location keys" + UserDBHelper.location);
//                Log.d("userLocs", "Users" + UserDBHelper.usernames);
                dbhelper.updateUser(user);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
