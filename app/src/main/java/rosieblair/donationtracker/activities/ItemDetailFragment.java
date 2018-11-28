package rosieblair.donationtracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rosieblair.donationtracker.database.ItemDBHelper;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Item;
//import model.Item;
//import model.ItemDatabase;

import rosieblair.donationtracker.R;

/**
 * Class to list item details
 */
public class ItemDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private ItemDBHelper itemDBHelper;
    private Item mItem;
    private LocationDBHelper locationHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDBHelper = new ItemDBHelper(getActivity());
        locationHelper = new LocationDBHelper(getActivity());
        Log.d("MYAPP", "arguments: " + getArguments());
        assert getArguments() != null;
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int item_id = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + item_id);
            mItem = itemDBHelper.getItem(item_id);

            Activity activity = this.getActivity();
            assert activity != null;
            CollapsingToolbarLayout appBarLayout = activity.findViewById(
                    R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getShortDescription());
            }
        }
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.time)).setText(getResources().getString(
                    R.string.time, mItem.getTime()));
            ((TextView) rootView.findViewById(R.id.location)).setText(getResources().getString(
                    R.string.place, locationHelper.getLocationByKey(mItem.getItemKey()).getName()));
            ((TextView) rootView.findViewById(R.id.short_desc)).setText(getResources().getString(
                    R.string.shortdesc, mItem.getShortDescription()));
            ((TextView) rootView.findViewById(R.id.full_desc)).setText(getResources().getString(
                    R.string.fulldesc, mItem.getFullDescription()));
            ((TextView) rootView.findViewById(R.id.value)).setText(getResources().getString(
                    R.string.value, mItem.getValue()));
            ((TextView) rootView.findViewById(R.id.category)).setText(getResources().getString(
                    R.string.category, mItem.getCategory()));
            ((TextView) rootView.findViewById(R.id.comments)).setText(getResources().getString(
                    R.string.comments, mItem.getComments()));
        }

        return rootView;
    }

}
