#include <Arduino.h>
#include <DHT.h>
#define Type DHT11

int sensePin = 2;
DHT HT(sensePin, Type);
float humidity;
float tempC;
float tempF;
int setTime = 500;
int dt = 1000;


void setup() {

  Serial.begin(9600);
  HT.begin();
  delay(setTime);


}

void loop() {
  humidity = HT.readHumidity();
  tempC = HT.readTemperature();
  tempF = HT.readTemperature(true);

  Serial.print("Humidity: ");
  Serial.print(humidity);
  Serial.print(" C: ");
  Serial.print(tempC);
  Serial.print(" F: ");
  Serial.println(tempF);
  delay(dt);

}


