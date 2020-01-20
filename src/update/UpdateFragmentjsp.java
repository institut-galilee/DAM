package com.example.navigation.ui.update;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.navigation.R;
import com.example.navigation.User;
import com.example.navigation.ui.send.SendViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateFragment extends Fragment implements LocationListener {

    private double  longitude ;
    private EditText nameUser;
    private EditText numPers;
    private double  latitude ;
    private TextView textView ;
    private UpdateViewModel updateViewModel;
    private Button update;
    private LocationManager locationManager;
    List<User> users;

    DatabaseReference databaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update, container, false);

        nameUser = (EditText) v.findViewById(R.id.name1);
        numPers = (EditText) v.findViewById(R.id.numPers2);
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        textView = (TextView) v.findViewById(R.id.textView5);
        update = (Button) v.findViewById(R.id.btnUpdate);

        update.setOnClickListener(new View.OnClickListener() {
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
                String name = numPers.getText().toString().trim();
                String num = numPers.getText().toString().trim();
                if (num.equals('1') ){
                    updateUser("@string/Per1", name,longitude,latitude);
                }
                if (num.equals('2') ){
                    updateUser("@string/Per2", name,longitude,latitude);
                }
                if (num.equals('3') ){
                    updateUser("@string/Per3", name,longitude,latitude);
                }
                if (num.equals('4') ){
                    updateUser("@string/Per4", name,longitude,latitude);
                }


            }
        });
        return v;
    }

    private boolean updateUser(String id, String name,double longt,double lat) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //updating artist
        User user = new User(id, name, longt,lat);
        dR.setValue(user);
        Toast.makeText(getContext().getApplicationContext(), "User Updated", Toast.LENGTH_LONG).show();
        return true;
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
}