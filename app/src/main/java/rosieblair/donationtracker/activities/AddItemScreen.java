package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.model.Item;
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

public class AddItemScreen extends AppCompatActivity {

//    private final AppCompatActivity activity = AddItemScreen.this;

    private EditText time;
    private Spinner location;
    private EditText shortDescription;
    private EditText fullDescription;
    private EditText value;
    private Spinner category;
    private TextView invalid_Location;
    private Button addButton;
    private Button cancelButton;
    private ArrayList<Location> temp;

    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        add();
        cancel();
    }

    public void add() {
//        time = (EditText) findViewById(R.id.enterDonateDate);
//        location = (Spinner) findViewById(R.id.selectLocation);
//        shortDescription= (EditText) findViewById(R.id.enterShortDescr);
//        fullDescription = (EditText) findViewById(R.id.enterFullDescr);
//        value = (EditText) findViewById(R.id.enterItemValue);
//        category = (Spinner) findViewById(R.id.selectItemCategory);
//
//        invalid_Location = (TextView) findViewById(R.id.invalidLocation);
//
//        addButton = (Button) findViewById(R.id.addItemButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loc = (Location) getIntent().getSerializableExtra("thisLocn");
//                ItemDatabase.times.add(time.getText().toString());
//                ItemDatabase.locations.add(null);  //need to change this
//                ItemDatabase.sDesriptions.add(shortDescription.getText().toString());
//                ItemDatabase.fDescriptions.add(fullDescription.getText().toString());
//                ItemDatabase.values.add(value.getText().toString());
//                ItemDatabase.categories.add(category.getSelectedItem().toString());
//                readCSVFile();

//                Log.d("Location name", "Location selected:" + location.getSelectedItem().toString());
////                Log.d("LocationListInstance", "LocationListInstance: " + temp.getItems());
//                Log.d("Location add", "Location add: " + findLocationByKey(UserDatabase.location.get(userIndex)).getName());
//                Log.d("LocationListInstance", "LocationListInstance: " + temp);
//                if (location.getSelectedItem().toString().equals(findLocationByKey(UserDatabase.location.get(userIndex)).getName())) {
//                    ItemDatabase.INSTANCE.getItems().add(new Item(time.getText().toString(), location.getSelectedItem().toString(),
//                            shortDescription.getText().toString(), fullDescription.getText().toString(),
//                            value.getText().toString(), category.getSelectedItem().toString(),++keyCounter));
//                    startActivity(intent);
//                if (loc != null) {
//                Item item = new Item(time.getText().toString(), loc.getName(),
//                        shortDescription.getText().toString(), fullDescription.getText().toString(),
//                        value.getText().toString(), category.getSelectedItem().toString(),++keyCounter);
//                ItemDatabase.INSTANCE.getItems().add(item);
//                Intent intent = new Intent(getApplicationContext(), EmployeeAppScreen.class); //change to item list screen
//                intent.putExtra("thisLocn", loc);
//                    List<Item> item_list = loc.getInventory();
//                    item_list.add(item);

//                    ItemDatabase.INSTANCE.getItems().add(new Item(time.getText().toString(), loc.getName(),
//                            shortDescription.getText().toString(), fullDescription.getText().toString(),
//                            value.getText().toString(), category.getSelectedItem().toString(),++keyCounter));
//                startActivity(intent);
//                } else {
//                    invalid_Location.setVisibility(View.VISIBLE); //when location employee doesnt select correct location to add item to, notify
//                }

//                currentItemDatabase();


//            }
//        });
    }

    public void cancel() {
//        Log.d("Cancel", "cancel register");
//        cancelButton = (Button) findViewById(R.id.cancelItemButton);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.EmployeeAppScreen");
//                startActivity(intent);
//            }
//        });
    }

    private void currentItemDatabase() {
//        for (int i = 0; i < ItemDatabase.INSTANCE.getItems().size(); i++) {
//            Log.d("Item", "Item: " + ItemDatabase.INSTANCE.getItems().get(i));
//
//        }
    }

    //use to read and load temporary list of locations to check if adding to right location
//    private void readCSVFile() {
//        temp = new ArrayList<>();

//        try {
//            //Open a stream on the raw file
//            InputStream is = getResources().openRawResource(R.raw.locationdata);
//            //From here we probably should call a model method and pass the InputStream
//            //Wrap it in a BufferedReader so that we get the readLine() method
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//
//            String line;
//            br.readLine(); //get rid of header line
//            while ((line = br.readLine()) != null) {
//                //Log.d(MainActivity.TAG, line);
//                String[] details = line.split(",");
//                int key = Integer.parseInt(details[0]);
//                boolean add;
//                if (temp.add(new Location(key, details[NAME_POSITION], details[LATITUDE_POSITION],
//                        details[LONGITUDE_POSITION], details[STREET_ADDRESS_POSITION], details[CITY_POSITION],
//                        details[STATE_POSITION], details[ZIP_CODE_POSITION], details[TYPE_POSITION],
//                        details[PHONE_NUMBER_POSITION], details[WEBSITE_POSITION]))) add = true;
//                else add = false;
//            }
//            br.close();
//        } catch (IOException e) {
//            //Log.e(MainActivity.TAG, "error reading assets", e);
//        }
//    }

    public Location findLocationByKey(int key) {
        for (Location d : temp) {
            if (d.getKey() == key) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }

}
