package rosieblair.donationtracker.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

public class ItemsByName extends AppCompatActivity {

    public static final String LOCATION = "location";
    public static final String NAME = "name";
    private ItemDBHelper itemhelper;
    private LocationDBHelper lochelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_by_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lochelper = new LocationDBHelper(this);
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
        recyclerView.setAdapter(new ItemsByName.SimpleItemRecyclerViewAdapter(itemhelper.findItemsByName(itemName)));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_list_of_items, parent, false);
            return new ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemsByName.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.item = mValues.get(position);
            //changed to get id because getKey() no longer valid
            holder.mIdView.setText("" + mValues.get(position).getId());
            holder.mContentView.setText(mValues.get(position).getShortDescription());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.item.getLocation());
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.item.getKey());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Item item;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}
