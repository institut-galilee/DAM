#include <TinyGPS++.h>
TinyGPSPlus gps;
double latitude, longitude;

#include <SoftwareSerial.h>
SoftwareSerial SIM800L(10, 11);


String response;
int lastStringLength = response.length();

String link;
//String SIM_PIN_CODE = String( "1661" );

void setup() { 
  Serial.begin(9600);
  Serial.println("Wait few seconds...");
  delay(5000);
  SIM800L.begin(9600);
  //SIM800L.print("AT+CPIN=");
  //SIM800L.println( SIM_PIN_CODE);
  delay(1000);
  Serial.println("GPS Start");
  SIM800L.println("AT+CMGF=1");
  delay(1000);
  SIM800L.println("AT+CNMI=2,2,0,0,0");
 
}

void loop() {

  if (SIM800L.available()){
      response = SIM800L.readStringUntil('\n');
  }
     

  if(lastStringLength != response.length()){
      GPS();
      //Perintah ON
      if(response.indexOf("ON") == 4){
          SIM800L.println("AT+CMGF=1");    //Sets the GSM Module in Text Mode
          delay(100);  // Delay of 1000 milli seconds or 1 second
          SIM800L.println("AT+CMGS=\"+3376*********\"\r"); 
          delay(100);
          SIM800L.println(link);// The SMS text you want to send
          delay(100);
          SIM800L.println((char)26);// ASCII code of CTRL+Z
          delay(100);
          Serial.println("Text send");
      }
  }
 
    
  
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
