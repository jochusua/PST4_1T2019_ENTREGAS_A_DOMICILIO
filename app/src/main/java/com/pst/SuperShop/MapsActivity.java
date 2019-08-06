package com.pst.SuperShop;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    public void onMapReady ( GoogleMap googleMap ) {
        Toast.makeText( this, "Mapa cargado", Toast.LENGTH_SHORT ).show();
        Log.d( TAG, "onMapReady: map is ready" );
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //GeekStore
        LatLng geekstore = new LatLng(-2.154969, -79.891575);
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        mMap.addMarker(new MarkerOptions().position(geekstore).title("GeekStore Mall del Sol"));

        //Funko
        LatLng funko = new LatLng( -2.109114, -79.948691 );
        mMap.addMarker( new MarkerOptions().position( funko ).title( "Funko Mall El Fortin" ).icon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_VIOLET ) ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( funko ) );

        //Zacatrus
        LatLng zacatrus = new LatLng( -2.228982, -79.898401);
        mMap.addMarker( new MarkerOptions().position( zacatrus ).title( "Zacatrus Mall del Sur" ).icon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_VIOLET ) ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( zacatrus ) );

        //GeekStore2
        LatLng geekstore2 = new LatLng(-2.176001, -79.943634);
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        mMap.addMarker(new MarkerOptions().position(geekstore2).title("GeekStore Riocentro Ceibos"));

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled( true );

        }
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps);
        getLocationPermission();
    }

    private void getDeviceLocation () {
        Log.d( TAG, "getDeviceLocation: getting the devices current location" );

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( this );

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener( new OnCompleteListener() {
                    @Override
                    public void onComplete ( @NonNull Task task ) {
                        if (task.isSuccessful()) {
                            Log.d( TAG, "onComplete: found location!" );
                            Location currentLocation = (Location) task.getResult();

                            moveCamera( new LatLng( currentLocation.getLatitude(), currentLocation.getLongitude() ), DEFAULT_ZOOM );

                        } else {
                            Log.d( TAG, "onComplete: current location is null" );
                            Toast.makeText( MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } catch (SecurityException e) {
            Log.e( TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera ( LatLng latLng, float zoom ) {
        Log.d( TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to User Location View
                .zoom(15)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setMyLocationButtonEnabled(true );
    }

    private void initMap () {
        Log.d( TAG, "initMap: initializing map" );
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.map );

        mapFragment.getMapAsync( MapsActivity.this );
    }

    private void getLocationPermission () {
        Log.d( TAG, "getLocationPermisszion: getting location permissions" );
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission( this.getApplicationContext(), FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission( this.getApplicationContext(), COURSE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions( this, permissions, LOCATION_PERMISSION_REQUEST_CODE );
            }
        } else {
            ActivityCompat.requestPermissions( this, permissions, LOCATION_PERMISSION_REQUEST_CODE );
        }
    }

    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ) {
        Log.d( TAG, "onRequestPermissionsResult: called." );
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d( TAG, "onRequestPermissionsResult: permission failed" );
                            return;
                        }
                    }
                    Log.d( TAG, "onRequestPermissionsResult: permission granted" );
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }
}
