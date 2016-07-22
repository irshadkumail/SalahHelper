package com.sodo.kumail.salahhelper;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by kumail on 7/15/2016.
 */

public class QiblaFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.activity_maps, viewGroup, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);

        return view;
    }

    public void onMapReady(GoogleMap map) {
        googleMap = map;
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);

        LatLng latLng= new LatLng(21.4225, 39.8262);
        googleMap.addMarker(new MarkerOptions().position(latLng)).setTitle("Kaaba");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


    }
}
