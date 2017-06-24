package com.example.rajpatel.ljietcloud.Activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rajpatel.ljietcloud.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ReachUs extends AppCompatActivity {

    static final LatLng position = new LatLng(22.9909 , 72.4878);
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reach_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
//            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//            Marker TP = googleMap.addMarker(new MarkerOptions().
//                    position(position).title("TutorialsPoint"));

            final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(22.9909, 72.4878), 12);
            googleMap.animateCamera(cameraUpdate);
            final MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(22.9909, 72.4878));
//            markerOptions.title("LJ college");

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(22.9909, 72.4878, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String cityName = addresses.get(0).getAddressLine(0);
            String stateName = addresses.get(0).getAddressLine(1);
            String countryName = addresses.get(0).getAddressLine(2);
            markerOptions.title(""+cityName+" "+stateName+" "+countryName);

            // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

            googleMap.addMarker(markerOptions);


//            final CircleOptions circleOptions = new CircleOptions();
//            circleOptions.radius(20000);
//            circleOptions.center(new LatLng(23, 72));
//            circleOptions.strokeWidth(5);
//            circleOptions.fillColor(Color.BLUE);
//            googleMap.addCircle(circleOptions);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
