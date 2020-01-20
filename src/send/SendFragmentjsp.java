package com.example.navigation.ui.slideshow;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.navigation.R;

public class SlideshowFragment extends Fragment {

    private EditText txtMobile;
    private EditText txtMessage;
    private Button btnSms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slideshow, container, false);
        txtMobile = (EditText) v.findViewById(R.id.mblTxt);
        txtMessage = (EditText) v.findViewById(R.id.msgTxt);
        btnSms = (Button) v.findViewById(R.id.btnSend);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("smsto:"));
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address", new String(txtMobile.getText().toString()));
                    i.putExtra("sms_body",txtMessage.getText().toString());
                    startActivity(Intent.createChooser(i, "Send sms via:"));
                }
                catch(Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel =
//                ViewModelProviders.of(this).get(SlideshowViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
//}

//
//    public SendFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v= inflater.inflate(R.layout.fragment_send,container, false);
//
//        return v;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Button btnEnvoie = (Button) getView().findViewById(R.id.envoyer);
//        //On récupère les deux EditText correspondant aux champs pour entrer le numéro et le message
//        final EditText numero = (EditText) getView().findViewById(R.id.numero);
//        final EditText message = (EditText) getView().findViewById(R.id.message);
//        //On affecte un écouteur d'évènement au bouton
//        btnEnvoie.setOnClickListener(new View.OnClickListener() {
//
//            @SuppressWarnings("deprecation")
//            public void onClick(View v) {
//                //On récupère ce qui a été entré dans les EditText
//                String num = numero.getText().toString();
//                String msg = message.getText().toString();
//                //Si le numéro est supérieur à 4 caractères et que le message n'est pas vide on lance la procédure d'envoi
//                if(num.length()>= 4 && msg.length() > 0){
//                    //Grâce à l'objet de gestion de SMS (SmsManager) que l'on récupère via la méthode static getDefault()
//                    //On envoie le SMS à l'aide de la méthode sendTextMessage
//                    SmsManager.getDefault().sendTextMessage(num, null, msg, null, null);
//                    //On efface les deux EditText
//                    numero.setText("");
//                    message.setText("");
//                }else{
//                    //On affiche un petit message d'erreur dans un Toast
//                   // Toast.makeText(getApplicationContext() , "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }
//
//
//    }