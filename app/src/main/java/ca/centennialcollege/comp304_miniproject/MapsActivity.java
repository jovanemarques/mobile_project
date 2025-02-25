package ca.centennialcollege.comp304_miniproject;

import android.os.LocaleList;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.centennialcollege.comp304_miniproject.models.Order;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent().getSerializableExtra("order") != null)
            order = (Order) getIntent().getSerializableExtra("order");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location;

        // 2019 and Java doesn't have a null operator.
        location = order != null ? new LatLng(order.getDeliveryAddress().getLatitude(), order.getDeliveryAddress().getLongitude()) : new LatLng(43.767840, -79.270550);

        if (order != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title("Order number: " + order.getNumber())
                    .snippet("Almost there, " + order.getClient().getName() + "!")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.delivery_truck)));
        } else {
            mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title("My Package!")
                    .snippet("Almost there!")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.delivery_truck)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.map_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
