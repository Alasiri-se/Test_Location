package com.example.testmap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity
        implements
        OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback
          {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

              /**
               * Flag indicating whether a requested permission has been denied after returning in
               * {@link #onRequestPermissionsResult(int, String[], int[])}.
               */
    private boolean permissionDenied = false;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
     //   fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
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
        map = googleMap;
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        enableMyLocation();
    }
              private void enableMyLocation() {
                  if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                          == PackageManager.PERMISSION_GRANTED) {
                      if (map != null) {
                          map.setMyLocationEnabled(true);
                      }
                  } else {
                      // Permission to access the location is missing. Show rationale and request permission
                     //PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                       //       Manifest.permission.ACCESS_FINE_LOCATION, true;
                      if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                          //we can show user a dialog of why this permisson is necessary
                          ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);

                      } else {
                          ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                  LOCATION_PERMISSION_REQUEST_CODE);
                      }
                  }
              }



    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }
              @Override
              public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                  if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
                      return;
                  }

                  if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                      // Enable the my location layer if the permission has been granted.
                      enableMyLocation();
                  }
                  else {
                      // Permission was denied. Display an error message
                      // Display the missing permission error dialog when the fragments resume.
                      permissionDenied = true;
                  }
              }

              @Override
              protected void onResumeFragments() {
                  super.onResumeFragments();
                  if (permissionDenied) {
                      // Permission was not granted, display error dialog.
                      showMissingPermissionError();
                      permissionDenied = false;
                  }
              }

              /**
               * Displays a dialog with error message explaining that the location permission is missing.
               */
              private void showMissingPermissionError() {
               //   PermissionUtils.PermissionDeniedDialog
                 //         .newInstance(true).show(getSupportFragmentManager(), "dialog");
              }
}