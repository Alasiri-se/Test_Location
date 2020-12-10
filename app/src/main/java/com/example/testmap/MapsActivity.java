package com.example.testmap;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PermissionGroupInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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

import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity
        implements
        OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMarkerClickListener {



    LatLng gothenburg = new LatLng(57.72635680726805, 11.963851620625237);
    ArrayList<LatLng> demo= new ArrayList<>();
    Dialog dialog;
    float hue= 197;
    // location name still do not work
    String locationName= "";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

              /**
               * Flag indicating whether a requested permission has been denied after returning in
               * {@link #onRequestPermissionsResult(int, String[], int[])}.
               */
    private boolean permissionDenied = false;
    private GoogleMap map;
    //widgets
    //private EditText mSearchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        dialog= new Dialog(this);
        //search
        //mSearchText = (EditText) findViewById(R.id.input_serch);
        // add markers.


        locations();



    }
    public void locations(){


        LatLng centralstation= new LatLng(57.70898611081937, 11.972313501612282);
        // buss 18
        LatLng korkarlens = new LatLng(57.75776700181472, 11.987768767718672);
        LatLng akkasGata = new LatLng(57.75423547557376, 11.980694006368749);
        LatLng selma = new LatLng(57.750959116253675, 11.98155493171513);
        LatLng sagensgatan = new LatLng(57.7459602856516, 11.977436318602667);
        LatLng kyrkogata = new LatLng(57.74315511118166, 11.974759748397986);
        LatLng bjorkRis = new LatLng(57.739016423628, 11.973734264982832);
        LatLng balladgatan = new LatLng(57.73162046108551, 11.975846258632135);
        LatLng brunnsBotorget = new LatLng(57.727478532206945, 11.9705199106782);
        LatLng brantinspl = new LatLng(57.7208366861881, 11.953674485475872);
        LatLng lillaBommen = new LatLng(57.711821776968414, 11.966031658214296);
        LatLng brunnsparken= new LatLng(57.707066682264276, 11.96718118326053);
        LatLng kungsPortsPlatsen= new LatLng(57.70417128936519, 11.969736269767381);
        LatLng valand= new LatLng(57.700342591223695, 11.974516512775526);
        LatLng vidblicksgatan= new LatLng(57.692388867573214, 11.979910269782106);
        LatLng spaldingsGatan= new LatLng(57.689649551935865, 11.983324712366983);
        LatLng bergsPrangare= new LatLng(57.68640478574476, 11.985556157668622);
        LatLng pilBagsGatan= new LatLng(57.684513046057695, 11.984646840085333);
        demo.add(spaldingsGatan);
        demo.add(vidblicksgatan);
        demo.add(valand);
        demo.add(kungsPortsPlatsen);
        demo.add(brunnsparken);
        demo.add(centralstation);
        demo.add(korkarlens);
        demo.add(akkasGata);
        demo.add(selma);
        demo.add(sagensgatan);
        demo.add(kyrkogata);
        demo.add(bjorkRis);
        demo.add(balladgatan);
        demo.add(brunnsBotorget);
        demo.add(brantinspl);
        demo.add(lillaBommen);
        demo.add(bergsPrangare);
        demo.add(pilBagsGatan);
    }

    //new method to search fältet
    /*
    private void init(){
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH
                            ||actionId==EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction()==keyEvent.ACTION_DOWN
                            || keyEvent.getAction()==keyEvent.KEYCODE_ENTER){
                    //EXECUTE OUR LOCATION FOR SEARCHING
                        geoLocate();
                }
                return false;
            }
        });

    }

     */

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
        //init();
        for(int i = 0;i<demo.size();i++){
            LatLng location= demo.get(i);
            String locationName= geoLocateToName(location.latitude,location.longitude);
            MarkerOptions options= new MarkerOptions().position(location).title(locationName);
            options.icon(BitmapDescriptorFactory.defaultMarker(hue));
            map.addMarker(options);
            //map.moveCamera(CameraUpdateFactory.newLatLng(demo.get(i)));

        }
        float zoomLevel = 11.0f; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(gothenburg,zoomLevel));
        map.setOnMarkerClickListener(this);
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
        Toast.makeText(this, "Current location", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
       // Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

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

    @Override
    public boolean onMarkerClick(final Marker marker) {
       // Toast.makeText(this,"My Position"+marker.getPosition(),
        //        Toast.LENGTH_LONG).show();


       String locationName= geoLocateToName(marker.getPosition().latitude,marker.getPosition().longitude);
        openDialog(locationName);


        /*
        Integer clickCount = (Integer) marker.getTag();
        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
            // Return false to indicate that we have not consumed the event and that we wish
            // for the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).

        }

         */
        return false;
    }

    private void openDialog(String n) {
    dialog.setContentView(R.layout.dialog_test);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    ImageView img = dialog.findViewById(R.id.imageView2);
    Button btn= dialog.findViewById(R.id.rate_b);
    TextView placeName= dialog.findViewById(R.id.place_name);
    placeName.setText(n);
    dialog.show();
    }
    // take lagLoT of a location and return the most possible name as string.
    private String  geoLocateToName(double la, double lo) {
        //Log.d(TAG,"geoLocate : geoLocation");
        //String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocation(la, lo, 1);
        } catch (IOException e) {
            //Log.e(TAG,"geoLocate: IOEXeption"+ e.getMessage());
        }
        if (list.size() > 0) {
            Address address = list.get(0);
            // Toast.makeText(this,address.getFeatureName(),Toast.LENGTH_LONG).show();
            return address.getFeatureName();
        }

        return null;
    }
    /*
    private void  geoLocate(){
        //Log.d(TAG,"geoLocate : geoLocation");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder= new Geocoder(MapsActivity.this);
        List <Address> list= new ArrayList<>();
        try {
            list= geocoder.getFromLocationName(searchString,1);
        }catch (IOException e){
            //Log.e(TAG,"geoLocate: IOEXeption"+ e.getMessage());
        }
        if(list.size()>0){
            Address address= list.get(0);
            Toast.makeText(this,address.toString(),Toast.LENGTH_LONG).show();
        }
    }

     */

}