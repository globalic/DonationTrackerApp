package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import rosieblair.donationtracker.R;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
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
