package rosieblair.donationtracker.activities;

import rosieblair.donationtracker.R;
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

import rosieblair.donationtracker.model.Item;
import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.database.LocationDBHelper;
//import model.Item;
//import model.ItemDatabase;

/**
 * Class showing search results after searching for items by name
 */
public class ItemsByName extends AppCompatActivity {

    public static final String LOCATION = "location";
    public static final String NAME = "name";
    private ItemDBHelper itemhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_by_name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LocationDBHelper lochelper = new LocationDBHelper(this);
        itemhelper = new ItemDBHelper(this);

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
        String itemName = getIntent().getStringExtra(NAME);
        Log.d("itemName", "" + itemName);
//        String locName = getIntent().getStringExtra(LOCATION);
//        Log.d("locName", "" + locName);
//        int locKey;
//        if (!(lochelper.getLocationByName(locName) == null)) {
//            locKey = lochelper.getLocationByName(locName).getKey();
//        } else {
//            locKey = -1;
//        }
        recyclerView.setAdapter(new ItemsByName.SimpleItemRecyclerViewAdapter(
                itemhelper.findItemsByName(itemName)));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        /**
         * Recycler view for showing all the resulting items
         * @param items list of items
         */
        SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        @NonNull
        public ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_list_of_items, parent, false);
            return new ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override public void onBindViewHolder(@NonNull final ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder
                                                     holder, int position) {
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

        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            Item item;

            /**
             * Viewholder for the recycler
             * @param view view for the holder
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
