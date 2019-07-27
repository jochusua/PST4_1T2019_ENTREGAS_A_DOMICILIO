package com.pst.SuperShop;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class ubicacion extends Fragment implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    public ubicacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_ubicacion, container, false);
        mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        if(mapFragment==null){
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            mapFragment=SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng guayaquil = new LatLng(-2.2058400, -79.9079500);
        mMap.addMarker(new MarkerOptions().position(guayaquil).title("Guayaquil"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guayaquil));
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }
}
