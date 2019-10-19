#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

const char* ssid = "amansourav";
const char* password = "amanaman";

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

String cardNumber;
int payStatus;
int tollNo;



void setup() {
  Serial.begin(9600);
  setup_wifi();
   Firebase.begin("parking-ee898.firebaseio.com","UAhPFufnwS1uGq7QysTdU046rg3Homju8oNIqkgs");  
 // cardNumber=Firebase.getString("CARD_NUMBER/Je8ze85QaQVbwDWuBRObv6Tkbgh2");
  
}
int val=5;
String value;
void loop() {
 // payStatus=Firebase.getInt("PAY_STATUS/Je8ze85QaQVbwDWuBRObv6Tkb");
 // tollNo=Firebase.getInt("TOLL_NO/Je8ze85QaQVbwDWuBRObv6Tkbgh2");
  val=Firebase.getInt("val/test");
  Firebase.setInt("val/run",10000);
  Serial.println(val);
  Serial.println("DONE");

}
