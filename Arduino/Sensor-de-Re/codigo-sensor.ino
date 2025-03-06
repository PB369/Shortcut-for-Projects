#define trigger 8
#define echo 7
#define buzzer 10

int distance = 0;
long duration;

void setup() {
  pinMode(trigger, OUTPUT);
  pinMode(echo, INPUT);
  pinMode(buzzer, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  digitalWrite(trigger, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigger, LOW);

  duration = pulseIn(echo, HIGH);
  distance = (duration*.0343)/2;  
  Serial.println(distance);

  if(distance < 90 && distance >= 50) {
    delay(150);
    tone(buzzer, 1000);
    delay(75);
    noTone(buzzer);
  } else if(distance < 50 && distance >= 25){
    delay(75);
    tone(buzzer, 1000);
    delay(75);
    noTone(buzzer);
  } else if (distance < 25 && distance >= 10){
    delay(25);
    tone(buzzer, 1000);
    delay(75);
    noTone(buzzer);
  } else if (distance < 10){
    tone(buzzer, 1000);
  }

  delay(100);
}
