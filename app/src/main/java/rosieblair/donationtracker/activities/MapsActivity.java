package rosieblair.donationtracker.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.LocationDBHelper;
import rosieblair.donationtracker.model.Location;

/**
 * Class to provide functionality of maps
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationDBHelper locDBhelper = new LocationDBHelper(MapsActivity.this);


//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //need to uncomment this and replace the location list stuff with location database
//        Log.d("MAP","Locations" + LocationList.INSTANCE.getLocations());
        for (Location loc: locDBhelper.locationList()) {
            LatLng locLL = new LatLng(Double.parseDouble(loc.getLatitude()),
                    Double.parseDouble(loc.getLongitude()));
            googleMap.addMarker(new MarkerOptions().position(locLL).title(
                    loc.getName()).snippet(loc.getPhoneNumber()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locLL));

        }
    }
}
