package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rosieblair.donationtracker.R;

/**
 * Class to allow functionality of employee app screen
 */
public class EmployeeAppScreen extends AppCompatActivity {

    private final AppCompatActivity activity = EmployeeAppScreen.this;

//    private String locName;

//    private User user;
//    private Location loc;

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
        pressViewMap();
    }

//    private void init() {
//
//    }
    private void pressViewMap() {
        Button viewMapButton = findViewById(R.id.map1);
        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    //                readCSVFile();
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void pressSearch() {
        Button searchButton = (Button) findViewById(R.id.searchButtonE);
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
        Button addDonationButton = (Button) findViewById(R.id.addItemButton);
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItemScreen.class);
                //intent.putExtra("thisLocn", loc);
                startActivity(intent);
            }
        });

    }

    private void pressViewLocations() {
        Button locationListButton = (Button) findViewById(R.id.locListButtonE);
        locationListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListOfLocations.class);
                startActivity(intent);
            }
        });

    }

    private void pressViewMyLocationInventory() {
        Button locationInventoryButton = (Button) findViewById(R.id.viewInventoryButton);
        locationInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListOfItems.class);
//                intent.putExtra("thisLocn", loc);
//                intent.putExtra(ListOfItems.LOCATION, locName);
                startActivity(intent);
            }
        });
    }

    /**
     * Method to redirect user to main app screen once logout
     * button is pressed
     * @param view the view
     */
    public void onLogOutPressed(View view) {
        Log.d("Edit", "logged out");
        Intent intent = new Intent(EmployeeAppScreen.this, MainActivity.class);
        startActivity(intent);
        //pop-up message notifying user that logout was successful
        Toast toast = Toast.makeText(getBaseContext(), "Logout successful!",
                Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        //setting the color of notification's background bubble
        toastView.getBackground().setColorFilter(Color.parseColor("#daeff1"),
                PorterDuff.Mode.SRC);
        toast.show();
    }

    public static final int NAME_POSITION = 1;
    public static final int LATITUDE_POSITION = 2;
    public static final int LONGITUDE_POSITION = 3;
    public static final int STREET_ADDRESS_POSITION = 4;
    public static final int CITY_POSITION = 5;
    public static final int STATE_POSITION = 6;
    public static final int ZIP_CODE_POSITION = 7;
    public static final int TYPE_POSITION = 8;
    public static final int PHONE_NUMBER_POSITION = 9;
    public static final int WEBSITE_POSITION = 10;

}
