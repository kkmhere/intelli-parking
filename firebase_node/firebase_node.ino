#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

const char* ssid = "SpecsGuy";
const char* password = "12345678901";
#define LED_RED_SLOT1 5 //d1
#define LED_GREEN_SLOT1 4//d2
#define LED_RED_SLOT2 14 //GREEN
#define LED_GREEN_SLOT2 12 //RED
#define IR1 13 //D7
#define IR2 15 //D8 
void setup_wifi() 
{
  delay(5);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  
}

int slot1Status;
int slot2Status;
int slot1IR;
int slot2IR;
int count=0;


void setup() {
  Serial.begin(9600);
  setup_wifi();
   Firebase.begin("parking-ee898.firebaseio.com","UAhPFufnwS1uGq7QysTdU046rg3Homju8oNIqkgs");  
 // cardNumber=Firebase.getString("CARD_NUMBER/Je8ze85QaQVbwDWuBRObv6Tkbgh2");
    pinMode(LED_GREEN_SLOT1,OUTPUT);
    pinMode(LED_RED_SLOT1,OUTPUT);  
    pinMode(LED_GREEN_SLOT2,OUTPUT);
    pinMode(LED_RED_SLOT2,OUTPUT);
}
int val=5;
int ct=0;
String value;
void loop() {
 // payStatus=Firebase.getInt("PAY_STATUS/Je8ze85QaQVbwDWuBRObv6Tkb");
 // tollNo=Firebase.getInt("TOLL_NO/Je8ze85QaQVbwDWuBRObv6Tkbgh2");
  //val=Firebase.getInt("val/final/test");
  //Firebase.setInt("val/run",10000);
  //Serial.println(val);
  Serial.println("DONE");
  slot1IR=digitalRead(IR1);
  slot2IR=digitalRead(IR2);
  slot1Status=Firebase.getInt("Led/ledStatus");
  slot2Status=Firebase.getInt("Led/ledStatus2");
  if(slot1Status==1)
  {
    digitalWrite(LED_RED_SLOT1,HIGH);
    digitalWrite(LED_GREEN_SLOT1,LOW);
    
  }
  else
  {
    digitalWrite(LED_RED_SLOT1,LOW);
    digitalWrite(LED_GREEN_SLOT1,HIGH);
  }

    if(slot2Status==1)
  {
    digitalWrite(LED_GREEN_SLOT2,HIGH);
    digitalWrite(LED_RED_SLOT2,LOW);
    
    
  }
  else
  {
    digitalWrite(LED_RED_SLOT2,HIGH);
    digitalWrite(LED_GREEN_SLOT2,LOW);
    
  }
  
  Firebase.setInt("val/status_slot_1",slot1IR);
  Firebase.setInt("val/status_slot_2",slot2IR);
  Serial.println("SLOT1:");
  Serial.print(slot1IR);
  Serial.println("SLOT2:");
  Serial.print(slot2IR);
  
  
  
  
  }
