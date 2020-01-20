#include <SoftwareSerial.h>
#include <String.h>
#include <TinyGPS++.h>
TinyGPSPlus gps;
double latitude, longitude;
String link;
SoftwareSerial sim800l(10, 11);

String number = "+33766107901"; //  le numero à qui on envoie le message
//String SIM_PIN_CODE = String( "1661" ); // le code pin de la carte sim

void setup() {
  delay(1000);
  Serial.begin(9600);
  Serial.println("Wait few seconds...");
  delay(5000);
  Serial.println("System Started...");
  delay(1000);
  sim800l.begin(9600);
  //sim800l.print("AT+CPIN=");
  //sim800l.println( SIM_PIN_CODE );
  Serial.println("Type c to make a call and s to send an SMS");
  if (sim800l.available() > 0)
    Serial.write(sim800l.read());
}

void loop() {
  if (Serial.available() > 0)
    switch (Serial.read())
    {
      case 'c':
        callNumber();
        break;
      case 's':
        SendMessage();
        break;
    }
  if (sim800l.available() > 0)
    Serial.write(sim800l.read());
}

void SendMessage()
{
  sim800l.println("AT+CMGF=1");
  delay(1000);
  sim800l.println("AT+CIPCSMLOC=1,1");
  delay(1000);
  sim800l.println("AT+CMGS=\"" + number + "\"\r");
  delay(1000);
  gps.encode(Serial.read());
  //String SMS = "111";
 if(gps.location.isUpdated()) {
    latitude = gps.location.lat();
    longitude = gps.location.lng();
    link = (latitude, 6)+"X"+(longitude, 6) ;
    sim800l.println(link);
  }

  delay(100);
  sim800l.println((char)26);
  delay(1000);
  sim800l.println();
}

void callNumber() {
  sim800l.print (F("ATD"));
  sim800l.print (number);
  sim800l.print (F(";\r\n"));
  delay(100);
  sim800l.println();
}

void GPS(){
  if(Serial.available()) {
    gps.encode(Serial.read());
  }
  if(gps.location.isUpdated()) {
    latitude = gps.location.lat();
    longitude = gps.location.lng();
    link = String(latitude, 6)+"X"+ String(longitude, 6) ;
    Serial.println(link);
  
  }
  
}
