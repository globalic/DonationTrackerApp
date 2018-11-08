package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.model.Item;
import rosieblair.donationtracker.model.Location;

/**
 * Class to show the inventory at a location
 */
public class ListOfItems extends AppCompatActivity {

    private final AppCompatActivity activity = ListOfItems.this;

    public static final String LOCATION = "location";
//    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
//        loc = (Location) getIntent().getSerializableExtra("thisLocn");
        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    }
}
