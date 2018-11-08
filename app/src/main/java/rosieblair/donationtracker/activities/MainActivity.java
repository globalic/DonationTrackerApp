package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Location;

import static rosieblair.donationtracker.activities.EmployeeAppScreen.CITY_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.LATITUDE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.LONGITUDE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.NAME_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.PHONE_NUMBER_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.STATE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.STREET_ADDRESS_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.TYPE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.WEBSITE_POSITION;
import static rosieblair.donationtracker.activities.EmployeeAppScreen.ZIP_CODE_POSITION;

/**
 * Class for the app screen that let's the user click to login or register
 */
public class MainActivity extends AppCompatActivity {

    private Button logButton;
    private Button regButton;

    private final AppCompatActivity activity = MainActivity.this;
    private LocationDBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new LocationDBHelper(activity);

        populateDB();
        pressLogin();
        pressRegister();
    }

    /**
     * Method to handle a login-button press, directs the user to Login screen.
     */
    public void pressLogin() {
        logButton = findViewById(R.id.loginButton);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method to handle a register-button press, directs user to Registration screen.
     */
    public void pressRegister() {
        regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
//    public static Context getContext() { return context; }

    /**
     * Method that loads the CVS file location data into the location database
     */
    public void populateDB() {
        List<Location> locs = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    is, Charset.forName("UTF-8")));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Location locn = new Location();
                String[] details = line.split(",");
                locn.setKey(Integer.parseInt(details[0]));
                locn.setName(details[NAME_POSITION]);
                locn.setLatitude(details[LATITUDE_POSITION]);
                locn.setLongitude(details[LONGITUDE_POSITION]);
                locn.setStreetAddress(details[STREET_ADDRESS_POSITION]);
                locn.setCity(details[CITY_POSITION]);
                locn.setState(details[STATE_POSITION]);
                locn.setZipCode(details[ZIP_CODE_POSITION]);
                locn.setLocType(details[TYPE_POSITION]);
                locn.setPhoneNumber(details[PHONE_NUMBER_POSITION]);
                locn.setWebsite(details[WEBSITE_POSITION]);
                locs.add(locn);
            }
            br.close();
        } catch (IOException e) {
            Log.d("ERROR", "locations not read from csv file");
        }
        if (!locs.isEmpty()) {
            for (Location l : locs) {
                dbhelper.addLocation(l);
            }
        }
    }

}
