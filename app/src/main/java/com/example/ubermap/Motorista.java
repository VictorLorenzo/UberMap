package com.example.ubermap;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Motorista extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    private Marker driveMarker=null;
    private MarkerOptions driveMarkerOption;

    private DatabaseReference drivePositionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.style_map));

        LatLng manaus = new LatLng(-3.141590, -60.020321);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(manaus));

    }

    @Override
    public void onMapClick(LatLng latLng) {
        LatLng newPosition = latLng;
        if(driveMarker!=null)driveMarker.remove();


        driveMarkerOption = new MarkerOptions();
        driveMarkerOption = new MarkerOptions().title("Sua Posição Atual").position(newPosition).icon(BitmapDescriptorFactory.fromResource(R.drawable.car));

        driveMarker = mMap.addMarker(driveMarkerOption);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newPosition));


        drivePositionReference = FirebaseDatabase.getInstance().getReference().child("position");

        drivePositionReference.child("Motorista").child("lat").setValue(newPosition.latitude);
        drivePositionReference.child("Motorista").child("long").setValue(newPosition.longitude);



    }

}