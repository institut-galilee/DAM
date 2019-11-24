 int buz =8;

 
 
 void setup() {
  pinMode(buz, OUTPUT);


  //start serial
  Serial.begin(9600);

}

void loop() {
  int i;

   if(Serial.available()>0){
    char lettre = Serial.read();
    if(lettre == '1'){
     
        tone(buz, 450);
        Serial.println(" Buzzer on");
  
      }

      
    
    else if (lettre == '0'){
      noTone(buz);
      Serial.println("Buzzer off");
    }
   }
}



     
    
 
