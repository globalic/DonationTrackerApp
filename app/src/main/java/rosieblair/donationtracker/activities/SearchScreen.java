package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

import rosieblair.donationtracker.R;

public class SearchScreen extends AppCompatActivity {

    private EditText item;            // item input
    private Spinner category;         // category spinner
    private Spinner location;         // location spinner
    private Button searchForItem;     // item search button
    private Button searchForCat;      // category search button
    //private Button searchForLoc;      // location search button
    //private String catName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pressSearchItem();
        pressSearchCat();
        //pressSearchLoc();
    }

    public void pressSearchItem() {
        location = (Spinner) findViewById(R.id.locSearchSpinner);
        item = (EditText) findViewById(R.id.enterSearchItem);
        searchForItem = (Button) findViewById(R.id.itemSearchButton);

        searchForItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = item.getText().toString();
                String locName = location.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), ItemsByName.class);
                        //"edu.gatech.micheyang.pbjdonationtracker.ItemsByName");
//                intent.putExtra(ItemsByName.NAME, itemName);
//                intent.putExtra(ItemsByName.LOCATION, locName);
                startActivity(intent);
            }
        });
    }

    public void pressSearchCat() {
        location = (Spinner) findViewById(R.id.locSearchSpinner);
        category = (Spinner) findViewById(R.id.catSearchSpinner);
        searchForCat = (Button) findViewById(R.id.catSearchButton);

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
