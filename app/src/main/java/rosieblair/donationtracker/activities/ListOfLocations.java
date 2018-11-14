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

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Location;

/**
 * Class that shows the location list
 */
public class ListOfLocations extends AppCompatActivity {
    private LocationDBHelper locDBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locDBhelper = new LocationDBHelper(ListOfLocations.this);
        setContentView(R.layout.activity_list_of_locations);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

//        locDBhelper = new LocationDBHelper(ListOfLocations.this);
        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(locDBhelper.locationList()));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Location> mValues;

        /**
         * Adapter that puts the list into a recycler view
         * @param items List of all locations
         */
        SimpleItemRecyclerViewAdapter(List<Location> items) {
            mValues = items;
        }

        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_list_of_locations, parent, false);
            return new ViewHolder(view);
        }

        @Override public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.location = mValues.get(position);
            Log.d("List of Locations", "" + mValues.get(position).getKey());
            Log.d("List of Locations", mValues.get(position).getName());
            holder.mIdView.setText("" + mValues.get(position).getKey());
            holder.mContentView.setText(mValues.get(position).getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LocationDetailActivity.class);
                Log.d("MYAPP", "Switch to detailed view for item: " + holder.location.getKey());
                intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, holder.location.getKey());

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
            Location location;

            /**
             * Method for the recycler view holder
             * @param view view for the recycler
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
