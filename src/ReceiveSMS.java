import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    public static String localisationRecueSms;



    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            if(bundle !=null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs=new SmsMessage[pdus.length];
                    for(int i=0;i<msgs.length;i++){
                        msgs[i]= SmsMessage.createFromPdu((byte[]) pdus[i]);
                        localisationRecueSms = msgs[i].getMessageBody();
                        Toast.makeText(context,"Le message est: "+localisationRecueSms,Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
    public static String getMessage(){
        return localisationRecueSms;
    }
}

