package com.example.findpt;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    private static final int PERMS_RECEIVE=4;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

    }

    protected void onResume(){
        super.onResume();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(mapFragment==null){
            return;
        }
        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        String latitudeLongitude=null;
        if(checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
             latitudeLongitude = ReceiveSms.getMessage();
            Toast.makeText(this,"latitudeLongitude not NULL",Toast.LENGTH_SHORT).show();
        }
        if(latitudeLongitude==null){
            Toast.makeText(this,"latitudeLongitude est NULL",Toast.LENGTH_SHORT).show();
            latitudeLongitude="45.736372X4.797354";
        }
        //En fait on recoit la localisation sous forme de message: "49.25655X2.254848" donc "latitudeXlongitude"

        String[] separation = latitudeLongitude.split("X");
        String latitude1 = separation[0];
        String longitude2 = separation[1];
        final double  lat1=Double.parseDouble(latitude1);
        final double long2=Double.parseDouble(longitude2);
        //On verifie toujours si on a la permission de localisation.
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {



                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    //Geocoder permet de traduire les infos de lat et long.
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    //Geocoder geocoder1= new Geocoder(getApplicationContext());
                    try {
                        List<Address> adressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = adressList.get(0).getLocality();
                        str += adressList.get(0).getPostalCode();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.addMarker(new MarkerOptions().position(new LatLng(lat1, long2)).title("FINDP&T").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude=location.getLatitude();
                    double longitude=location.getLongitude();
                    LatLng latLng=new LatLng(latitude,longitude);
                    //Geocoder permet de traduire les infos de lat et long.
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try{
                        List<Address> adressList = geocoder.getFromLocation(latitude,longitude,1);
                        String str=adressList.get(0).getLocality();
                        str+=adressList.get(0).getPostalCode();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }

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

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10.2f);
    }
}

