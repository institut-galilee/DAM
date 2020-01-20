package com.example.navigation.ui.tools;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.navigation.R;
import com.example.navigation.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.getSystemService;

public class ToolsFragment extends Fragment implements LocationListener {

    public static final String USER_NAME = "net.simplifiedcoding.firebasedatabaseexample.usertname";
    public static final String USER_ID = "net.simplifiedcoding.firebasedatabaseexample.userid";

    private EditText nameUser;
    private Button ajouter;

    private LocationManager locationManager;
    ListView listViewUsers;
    private double longitude;
    private double latitude;
    private TextView textView;

    //a list to store all the artist from firebase database
    List<User> users;

    DatabaseReference databaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tools, container, false);
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        nameUser = (EditText) v.findViewById(R.id.name);
        textView = (TextView) v.findViewById(R.id.textView3);
        ajouter = (Button) v.findViewById(R.id.btnAjouter);

        users = new ArrayList<>();
        ajouter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

                if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Location location;
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(Objects.requireNonNull(location));
                addUser();
            }
        });
        return v;
    }
//
//    protected void onResume(){
//        super.onResume();
//        checkPermission();
//    }
//
//    private void checkPermission() {
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,new String []{
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//            },PERMS_CALL_ID);
//
//            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
//            }
//            if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
//                lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,10000,0,this);
//            }
//            if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
//                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER?10000,0,this);
//            }
//    }

    private void addUser() {

//        if ((ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//                && (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//
//            final Location l = this.lm.getLastKnownLocation(
//                    LocationManager.PASSIVE_PROVIDER);
//            longitude = l.getLongitude();
//            latitude = l.getLatitude();
//        }


//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
//         Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();



        String name = nameUser.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseUser.push().getKey();

           User user = new User( id,name, longitude ,latitude);

            //Saving the Artist
            databaseUser.child(id).setValue(user);

            nameUser.setText("");


            Toast.makeText(getContext().getApplicationContext(), "Artist added", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(getContext().getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        textView.setText("longitude : " + longitude +"\n" + "latitude :" + latitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
//        @Override
//        public void onRequestPermissionsResult(int requestCode,){
//            super.onRequestPermissionsResult(requestCode,permission,grantResult);
//            if (requestCode == PERMS_CALLID){
//                checkPermission();
//            }
//        }

}