package rosieblair.donationtracker.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Location;

/**
 * A fragment representing a single Location detail screen.
 * This fragment is either contained in a {@link ListOfLocations}
 * in two-pane mode (on tablets) or a {@link LocationDetailActivity}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment {
    private LocationDBHelper locDBhelper;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
//        public static final int ARG_ITEM_ID = 3;
    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public LocationDetailFragment() {
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locDBhelper = new LocationDBHelper(getActivity());

        assert getArguments() != null;
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int item_id = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + item_id);
            mItem = locDBhelper.getLocationByKey(item_id);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity
                    .findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_location_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {

            ((TextView) rootView.findViewById(R.id.phone_number))
                    .setText(getResources().getString(R.string.phone_num, mItem.getPhoneNumber()));
            ((TextView) rootView.findViewById(R.id.website))
                    .setText(getResources().getString(R.string.website, mItem.getWebsite()));
            ((TextView) rootView.findViewById(R.id.longitude))
                    .setText(getResources().getString(R.string.longitude, mItem.getLongitude()));
            ((TextView) rootView.findViewById(R.id.latitude))
                    .setText(getResources().getString(R.string.latitude, mItem.getLatitude()));
            ((TextView) rootView.findViewById(R.id.street_address))
                    .setText(getResources().getString(R.string.address, mItem.getStreetAddress()));
            ((TextView) rootView.findViewById(R.id.city))
                    .setText(mItem.getCity());
            ((TextView) rootView.findViewById(R.id.state))
                    .setText(mItem.getState());
            ((TextView) rootView.findViewById(R.id.zip_code))
                    .setText(mItem.getZipCode());
        }

        return rootView;
    }
}
