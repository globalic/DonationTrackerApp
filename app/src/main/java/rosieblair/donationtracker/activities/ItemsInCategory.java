package rosieblair.donationtracker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Item;
import rosieblair.donationtracker.R;

/**
 * Class to get item count and toolbar creation
 */
public class ItemsInCategory extends AppCompatActivity {

    private static final String CATEGORY = "category";
    private static final String LOCATION = "location";
    private ItemDBHelper itemhelper;
    private LocationDBHelper lochelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_in_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No search results found", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        String catName = getIntent().getStringExtra(CATEGORY);
        Log.d("catName", "" + catName);
        String locName = getIntent().getStringExtra(LOCATION);
        Log.d("locName", "" + locName);
        int locKey;
        if (!(lochelper.getLocationByName(locName) == null)) {
            locKey = lochelper.getLocationByName(locName).getKey();
        } else {
            locKey = -1;
        }
        recyclerView.setAdapter(new ItemsInCategory.SimpleItemRecyclerViewAdapter(itemhelper
                .findItemsByCategory(catName, locKey)));
    }

    /**
     * Class to recycle items in adapter
     */
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        /**
         * Adapter for recycler view
         * @param items list of items
         */
        SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        @NonNull
        public ItemsInCategory.SimpleItemRecyclerViewAdapter
                .ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_list_of_items, parent, false);
            return new ItemsInCategory.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override public void onBindViewHolder(@NonNull final ItemsInCategory.SimpleItemRecyclerViewAdapter
                .ViewHolder holder, int position) {
            holder.item = mValues.get(position);
            //changed to get id because getKey() no longer valid
            holder.mIdView.setText("" + mValues.get(position).getId());
            holder.mContentView.setText(mValues.get(position).getShortDescription());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);


                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * Class to make viewholder
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            Item item;

            /**
             * Viewholder for the recycler
             * @param view View for the holder
             */
            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = view.findViewById(R.id.id);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            @NonNull
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}
