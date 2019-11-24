

#  LAB 2 


# EXERCISES 

# **EXERCISE 1 : BUZZER**

   **1.PLOT**
   ![WhatsApp Image 2019-11-24 at 7 09 26 PM (1)](https://user-images.githubusercontent.com/56651688/69501571-6fe94380-0f06-11ea-963d-2dfb791c9f6b.jpeg)
 
 
   **2.SKETCHS**
     
   ![exobuz](https://user-images.githubusercontent.com/56651688/69500883-9bb4fb00-0eff-11ea-80c4-6dd2cb804081.png)
   **Vue schématique** 
   
   ![exo2ldrshema](https://user-images.githubusercontent.com/56651688/69500882-9a83ce00-0eff-11ea-968a-d7d491a2f640.png)
  
   **3.CODES**
   
   ```
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
```

# **EXERCISE 2 : LDR**
  **1.PLOT**
   ![WhatsApp Image 2019-11-24 at 7 09 26 PM](https://user-images.githubusercontent.com/56651688/69500535-5cd17600-0efc-11ea-9dff-87385685ca4c.jpeg)
  

  **2.SKETCHS**
    **Sans la led**
   ![exo2ldr](https://user-images.githubusercontent.com/56651688/69500881-98ba0a80-0eff-11ea-8625-1623febcf201.png)
 
  **Avec la led**
  ![exoldrled](https://user-images.githubusercontent.com/56651688/69500885-9d7ebe80-0eff-11ea-897e-99cc88b97586.png)
  
  
   **Vue schématique** 
   
![exoldrledshema](https://user-images.githubusercontent.com/56651688/69500886-9eafeb80-0eff-11ea-8d7a-07c43419ca0a.png)
   
 
  **3.CODES**
  
  **Sans la led**
   ```
int sensorPin = A0; // select the input pin for LDR

int sensorValue = 0; // variable to store the value coming from the sensor

void setup() {
  Serial.begin(9600); //sets serial port for communication
}
void loop() {
  sensorValue = analogRead(sensorPin); // read the value from the sensor
  Serial.println(sensorValue); //prints the values coming from the sensor on the screen

  delay(100);

}

 ```
 **Avec la led**
 ```
const int ledPin = 13;
const int ldrPin = A0;

void setup() {
  Serial.begin (9600);
  pinMode(ledPin, OUTPUT);
  pinMode(ldrPin, INPUT);

}

void loop() {
  int ldrStatus = analogRead (ldrPin);

  if(ldrStatus <= 300){
    digitalWrite(ledPin, HIGH);
    Serial.println("LDR is DARK, LED is ON");
  }
  else {
    digitalWrite (ledPin, LOW);
    Serial.println("----------------------");
  }
}
 ```
 **4.GRAPH**
 
**Voici le graphe qui varie en fonction de la luminosité dédecté par le LDR**
![WhatsApp Image 2019-11-24 at 10 07 31 PM (1)](https://user-images.githubusercontent.com/56651688/69501622-0584d300-0f07-11ea-8ae7-fa810594c895.jpeg)
