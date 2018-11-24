package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.model.Location;

import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Item;
import static rosieblair.donationtracker.model.Item.keyCounter;
/**
 * Class to allow functionality of adding items
 */
public class AddItemScreen extends AppCompatActivity {

//    private final AppCompatActivity activity = AddItemScreen.this;

    private EditText time;
    private Spinner location;
    private EditText shortDescription;
    private EditText fullDescription;
    private EditText value;
    private Spinner category;
    private Iterable<Location> temp;

    private Location loc;
    private Item newItem;
    private ItemDBHelper itemhelper;
    private LocationDBHelper lochelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add();
        cancel();
    }

    /**
     * Method used to add item into inventory
     */
    private void add() {

        lochelper = new LocationDBHelper(AddItemScreen.this);
        itemhelper = new ItemDBHelper(AddItemScreen.this);
        time = findViewById(R.id.enterDonateDate);
        location = findViewById(R.id.selectLocation);
        shortDescription= findViewById(R.id.enterShortDescr);
        fullDescription = findViewById(R.id.enterFullDescr);
        value = findViewById(R.id.enterItemValue);
        category = findViewById(R.id.selectItemCategory);


        TextView invalid_Location = findViewById(R.id.invalidLocation);

        List<Location> locList = lochelper.locationList();
        String[] spinEntries = new String[locList.size()];
        for (int i = 0; i < locList.size(); i++) {
            spinEntries[i] = locList.get(i).getName();
        }

        ArrayAdapter<String> spinAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinEntries);
        spinAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(spinAdapt);


        Button addButton = findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String locName = location.getSelectedItem().toString();
                loc = lochelper.getLocationByName(locName);
                int locKey = loc.getKey();
                newItem = new Item();
                newItem.setTime(time.getText().toString());
                newItem.setShortDescription(shortDescription.getText().toString());
                newItem.setFullDescription(fullDescription.getText().toString());
                newItem.setValue(value.getText().toString());
                newItem.setCategory(category.getSelectedItem().toString());
                newItem.setItemKey(locKey);
                newItem.setId(++keyCounter);
                itemhelper.addItem(newItem);

                Intent intent = new Intent(getApplicationContext(), EmployeeAppScreen.class);
//                intent.putExtra("locKey", locKey);
                startActivity(intent);
            }
        });

    }

    /**
     * Method to cancel addition of item
     */
    private void cancel() {
        Log.d("Cancel", "cancel register");
        Button cancelButton = findViewById(R.id.cancelItemButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeAppScreen.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Method used to find location by key
     * @param key key used to find location
     * @return the requested location to find
     */
    public Location findLocationByKey(int key) {
        for (Location d : temp) {
            if (d.getKey() == key) {
                return d;
            }
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }

}

