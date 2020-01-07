package com.example.findpt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class Ecran2 extends AppCompatActivity implements LocationListener {

    private LocationManager lm;
    private static final int PERMS_CALL_ID = 1; // id de l'appel sur les permissions de localisation.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
        //Les request LOCATION UPDATE nécessitent que l'utilisateur ait autorisé l'application a accéder a sa localisation.

    }

    private void checkPermissions(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},PERMS_CALL_ID
            );
            return;
        }
        // A partir d'ici, on sait que l'utilisateur a autorisé l'app a accéder a la localisation. On affiche un message (en Toast).
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Demande sur le systeme d'une nouvelle localisation chaque 10 secondes (10000ms).

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            // Demande sur le systeme d'une nouvelle localisation chaque 10 secondes (10000ms).
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,10000,0,this);
        }
        if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            // Demande sur le systeme d'une nouvelle localisation chaque 10 secondes (10000ms).
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0,this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(lm != null){
            lm.removeUpdates(this);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();

    }
}
