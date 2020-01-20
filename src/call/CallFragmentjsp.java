package com.example.navigation.ui.gallery;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.navigation.R;



public class GalleryFragment extends Fragment {


    private Button button;
    private EditText txtMobile;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_call, container, false);

        txtMobile = (EditText) v.findViewById(R.id.num);
        button = (Button) v.findViewById(R.id.btnCall);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Uri u = Uri.parse("tel:" + txtMobile.getText().toString());
                    Intent i = new Intent(Intent.ACTION_DIAL, u);
                    Toast.makeText(getActivity().getApplicationContext(), "clicked", Toast.LENGTH_LONG)
                            .show();
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Call Failed to emitted, Please try again", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

            return v;
        }

    }


