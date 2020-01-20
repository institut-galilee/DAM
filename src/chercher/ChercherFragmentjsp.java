package com.example.navigation.ui.chercher;

        import android.Manifest;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.annotation.RequiresApi;
        import androidx.core.content.ContextCompat;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;

        import com.example.navigation.R;
        import com.example.navigation.User;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;

public class ChercherFragment extends Fragment {
//
//    public static final String USER_NAME = "net.simplifiedcoding.firebasedatabaseexample.usertname";
//    public static final String USER_ID = "net.simplifiedcoding.firebasedatabaseexample.userid";
//
//    private EditText nameUser;
//    private Button chercher;
//
//    private LocationManager locationManager;
//
//    private TextView textView;
//
//    //a list to store all the artist from firebase database
//    List<User> users;
//
//    DatabaseReference databaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chercher, container, false);
//        databaseUser = FirebaseDatabase.getInstance().getReference("users");

//        nameUser = (EditText) v.findViewById(R.id.name1);
//        textView = (TextView) v.findViewById(R.id.textView4);
//        chercher = (Button) v.findViewById(R.id.btnChercher);

//        chercher.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        return v;
    }
}