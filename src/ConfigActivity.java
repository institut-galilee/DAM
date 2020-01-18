package com.example.findpt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity  extends AppCompatActivity {


    private static final int PERMS_CALL_ID = 2;
    private static final int PERMS_CALL_LOC_ID=3;

    EditText number;
    Button bouton;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        number = findViewById(R.id.editNumero);
        bouton=findViewById(R.id.button_valider);
        bouton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                envoyerSMS();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void envoyerSMS(){
        if ( checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                   Manifest.permission.SEND_SMS},PERMS_CALL_ID
            );
            return;
        }


        // A partir d'ici, on sait que l'utilisateur a autorisé l'app  a envoyer des messages. On affiche un message (en Toast).
        Toast.makeText(this,"Vous avez autorisé FINDP&T à envoyer/recevoir des messages.",Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, PERMS_CALL_LOC_ID
            );
            return;
        }
        Toast.makeText(this,"Vous avez autorisé FINDP&T à accéder a votre localisation.",Toast.LENGTH_SHORT).show();
        if((ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )) {
            return;
        }
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

        if((ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )) {
            return;
        }
        // Attention, Network provider est plus precis que GPS provider, mais des fois le gps provider n'est pas disponible ce qui renvoie NULL!!!
        Location location=null;
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if(location==null){
            return;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        String numeroTel = number.getText().toString();
        String lat=String.valueOf(latitude);
        String longi=String.valueOf(longitude);
        String leMessage=lat+"X"+longi;
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numeroTel, null, leMessage, null, null);
            Toast.makeText(this,"Informations de localisation envoyées!",Toast.LENGTH_SHORT).show();
        }



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMS_CALL_ID: {
                envoyerSMS();
            }
            case PERMS_CALL_LOC_ID:{
                envoyerSMS();
            }

        }
    }
}
