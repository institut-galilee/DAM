package com.example.navigation.ui.home;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.navigation.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.makeText;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    DatabaseReference dref;
    Float longitudeNum ;
    Float latitudeNum;
    String latitudeChaine;
    String longitudeChaine;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_contact_us,container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng  pp= new LatLng(48.8566969,2.3514616);
        map.addMarker(new MarkerOptions().position(pp).title("Restaurante Ilobasco"));

        float zoomlevel = 16;

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp,zoomlevel));
    }
}