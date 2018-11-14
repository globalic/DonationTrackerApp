package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Location;

/**
 * Class to search for donated items
 */
public class SearchScreen extends AppCompatActivity {

    private EditText item;            // item input
    private Spinner category;         // category spinner
    private Spinner location;         // location spinner
    //private Button searchForLoc;      // location search button
    private LocationDBHelper lochelper;
    //private String catName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lochelper = new LocationDBHelper(this);
        ItemDBHelper itemhelper = new ItemDBHelper(this);
        pressSearchItem();
        pressSearchCat();
        //pressSearchLoc();
    }

    /**
     * Method that handles searching items from location selection and input
     */
    private void pressSearchItem() {
        location = findViewById(R.id.locSearchSpinner);
        item = findViewById(R.id.enterSearchItem);
        Button searchForItem = findViewById(R.id.itemSearchButton);

        List<Location> locList = lochelper.locationList();
        String[] spinEntries = new String[locList.size()];
        for (int i = 0; i < locList.size(); i++) {
            spinEntries[i] = locList.get(i).getName();
        }

        ArrayAdapter<String> spinAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinEntries);
        spinAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(spinAdapt);

        searchForItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = item.getText().toString();
                //Item item = itemhelper.findItemsByName(itemName).get(0);
                String locName = location.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), ItemsByName.class);
                        //"edu.gatech.micheyang.pbjdonationtracker.ItemsByName");
                intent.putExtra(ItemsByName.NAME, itemName);
                //intent.putExtra(ItemsByName.LOCATION, item.locName);
//                intent.putExtra(ItemsByName.LOCATION, );
                startActivity(intent);
            }
        });
    }

    /**
     * Method that handles searching items from location and category selection
     */
    private void pressSearchCat() {
        location = findViewById(R.id.locSearchSpinner);
        category = findViewById(R.id.catSearchSpinner);
        Button searchForCat;
        searchForCat = findViewById(R.id.catSearchButton);

        searchForCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String catName = category.getSelectedItem().toString();
                String locName = location.getSelectedItem().toString();
//                Intent intent = new Intent(getApplicationContext(), ItemsInCategory.class);
//                        //"edu.gatech.micheyang.pbjdonationtracker.ItemsInCategory");
//                intent.putExtra(ItemsInCategory.CATEGORY, catName);
//                intent.putExtra(ItemsInCategory.LOCATION, locName);
//                startActivity(intent);
            }
        });
    }
}
