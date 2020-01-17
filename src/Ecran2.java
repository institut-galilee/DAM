
package com.example.findpt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button button_carte=findViewById(R.id.button_carte);
        Button button_tutoriel=findViewById(R.id.button_tutoriel);
        Button button_config=findViewById(R.id.button_config);
        //Lorsque on appuye sur le bouton 'Tutoriel'
        button_tutoriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirTutoActivity();
            }
        });
        // Lorsque on appuye sur le bouton 'Afficher la carte' alors le code appelle la methode CheckPermissions.
        button_carte.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });
        //Lorsque on appuye sur le bouton 'Configurqtion' on accede a ConfigActivity
        button_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirConfigActivity();
            }
        });
        //Les request LOCATION UPDATE nécessitent que l'utilisateur ait autorisé l'application a accéder a sa localisation.

    }
    public void ouvrirConfigActivity(){
        Intent intent = new Intent(this,ConfigActivity.class);
        startActivity(intent);
    }
    public void ouvrirTutoActivity(){
        Intent intent = new Intent(this,TutorielActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        Toast.makeText(Ecran2.this,"Vous avez autorisé FINDP&T à acceder à votre localisation.",Toast.LENGTH_SHORT).show();
        // On ouvre donc l'activity de la carte Google Maps.
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
