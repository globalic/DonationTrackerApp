package rosieblair.donationtracker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.model.Item;

//import model item database and stuff

public class ListOfItems extends AppCompatActivity {
    public static final String LOCATION = "location";
    private ItemDBHelper itemDBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDBhelper = new ItemDBHelper(ListOfItems.this);
        setContentView(R.layout.activity_list_of_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        int locKey = getIntent().getIntExtra("locKey", 0);
//        String locName = "AFD Station 4";
//        recyclerView.setAdapter(new ListOfItems.SimpleItemRecyclerViewAdapter(itemDBhelper.itemList()));
        Log.d("LocName", "" + locKey);
        recyclerView.setAdapter(new ListOfItems.SimpleItemRecyclerViewAdapter(itemDBhelper.getLocationInventory(locKey)));
//        ItemDatabase.INSTANCE.findItemsAtLocation(LocationList.INSTANCE.findLocationByKey(UserDatabase.location.get(userIndex)).getName()))
//        ItemDatabase.INSTANCE.findItemsAtLocation(locName)
    }
    //    public ArrayList<Item> findItemsAtLocation(String location) {
//        ArrayList<Item> itemsAtLoc = new ArrayList<>();
//        for (Item d : ItemDatabase.INSTANCE.getItems()) {
//            if (d.getLocation().equals(location)) itemsAtLoc.add(d);
//        }
//        Log.d("MYAPP", "Warning - Failed to find items for: " + location);
//        return itemsAtLoc;
//    }
//    public Location findLocationByKey(int key) {
//        for (Location d : LocationList.INSTANCE.getItems()) {
//            if (d.getKey() == key) return d;
//        }
//        Log.d("MYAPP", "Warning - Failed to find key: " + key);
//        return null;
//    }
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_list_of_items, parent, false);
            return new ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.item = mValues.get(position);
            holder.mIdView.setText("" + mValues.get(position).getItemKey());
            holder.mContentView.setText(mValues.get(position).getShortDescription());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.item.getItemKey());

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