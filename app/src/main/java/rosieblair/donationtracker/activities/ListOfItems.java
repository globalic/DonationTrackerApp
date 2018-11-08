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
//        String locName = loc.getName();
//        recyclerView.setAdapter(new ListOfItems.SimpleItemRecyclerViewAdapter(ItemDatabase.INSTANCE.getItems()));
//        Log.d("LocName", "" + locName);
//        recyclerView.setAdapter(new ListOfItems.SimpleItemRecyclerViewAdapter(ItemDBHelper.findItemsAtLocation(locName)));
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
//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<Item> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<Item> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.content_list_of_items, parent, false);
//            return new ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ListOfItems.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
//            holder.item = mValues.get(position);
//            holder.mIdView.setText("" + mValues.get(position).getKey());
//            holder.mContentView.setText(mValues.get(position).getShortDescription());
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.item.getLocation());
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.item.getKey());
//
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public Item item;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//    }

}
