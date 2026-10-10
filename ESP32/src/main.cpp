#include <Arduino.h>
#include <WiFi.h>
#include <DHT.h>
#include <HTTPClient.h>

#define SOIL_PIN 34
#define DHT_PIN 23
#define SOIL_POWER_PIN 32

#define Type DHT11


const char* ssid = "SteppersHouse";
const char* password = "zagsgotdudes";
DHT HT(DHT_PIN, Type);
const char* django_url = "http://FILL IN W/ DJANGO";

float humidity;
float temp_c;
float temp_f;
int setTime = 1000;
int dt = 1000;
float soil_moist;
int soil_t = 200;


void setup() {
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);

  WiFi.begin(ssid, password);
  Serial.print("Connecting to wifi");


  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println();
  Serial.println("Connected");

  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());

  HT.begin();
  delay(setTime);

  pinMode(SOIL_POWER_PIN, OUTPUT);

  

}

void loop() {

  // DHT11 readings  
  humidity = HT.readHumidity();
  temp_c = HT.readTemperature();
  temp_f = HT.readTemperature(true);
  


  // Soil Moisture readings
  digitalWrite(SOIL_POWER_PIN, HIGH);
  delay(soil_t);
  soil_moist = analogRead(SOIL_PIN);
  digitalWrite(SOIL_POWER_PIN, LOW);

  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    http.begin(django_url);

    http.addHeader("Content-Type", "applications/json");
  }





  // Serial.print("Data:");
  // Serial.print(humidity);
  // Serial.print(",");
  // Serial.print(temp_f);
  // Serial.print(",");
  // Serial.print(temp_c);
  // Serial.print(",");
  // Serial.println(soil_moist);

  delay(dt);
  
}


