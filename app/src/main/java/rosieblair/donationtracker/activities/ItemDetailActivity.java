package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import rosieblair.donationtracker.R;

/**
 * Class to allow functionality of item selection
 */
public class ItemDetailActivity extends AppCompatActivity {
    public static int locKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getIntExtra("locKey",0) != 0) {
            locKey = getIntent().getIntExtra("locKey",0);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(ItemDetailFragment.ARG_ITEM_ID, 1000));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            Intent intent = new Intent(this, ListOfItems.class);
            intent.putExtra("locKey", locKey);
            navigateUpTo(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
