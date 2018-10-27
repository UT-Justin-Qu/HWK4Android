package com.example.hwk4;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



import android.location.Address;
import android.location.Geocoder;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String place;
    private double latit;
    private double longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        setContentView(R.layout.activity_maps);
        place = message;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

    public ArrayList<Double> getCoord() {
        URL placeUrl;
        HttpURLConnection connection = null;
        try {
            //  placeUrl = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + place + "INSERTKEYHERE");
            // connection = (HttpURLConnection) placeUrl.openConnection();
            // connection.setRequestMethod("GET");
            Geocoder location = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> results = location.getFromLocationName(place, 1);
                ArrayList<Double> arr = new ArrayList<>(2);
                arr.add(results.get(0).getLatitude());
                arr.add(results.get(0).getLongitude());
                return arr;

            } catch (Exception e) {

            }
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Double> array= getCoord();
        LatLng sydney = new LatLng(array.get(0), array.get(1));
        mMap.addMarker(new MarkerOptions().position(sydney).title(place));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



}
