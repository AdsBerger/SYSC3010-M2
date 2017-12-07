int opto = 12;
int led = 13;
int x = 0;
void setup()
{
  pinMode(opto, OUTPUT);
  pinMode(led, OUTPUT);
  Serial.begin(9600);
}

void loop()
{
  if(Serial.available())
  {
    x = (Serial.read() - '0');
    
  }

  if(x==0 || x != 1 || x != 2 || x != 3)
  {
    //do nothing...wait
  }
//________PRESET 1___________________________  
  if(x==1)
  {
  for(int i = 0; i < 15; i++)
  {
  delay(1400);
  digitalWrite(opto, HIGH);
  digitalWrite(led, HIGH);
  delay(100);
  digitalWrite(opto, LOW);
  digitalWrite(led, LOW);
  }
  x = 0;
  }
//___________________________________________


//________PRESET 2___________________________  
  if(x==2)
  {
  for(int i = 0; i < 30; i++)
  {
  delay(1400);
  digitalWrite(opto, HIGH);
  digitalWrite(led, HIGH);
  delay(100);
  digitalWrite(opto, LOW);
  digitalWrite(led, LOW);
  }
  x = 0;
  }
//___________________________________________

//________PRESET 3___________________________  
  if(x==3)
  {
  delay(1500);
  digitalWrite(opto, HIGH);
  digitalWrite(led, HIGH);
  delay(100);
  digitalWrite(opto, LOW);
  digitalWrite(led, LOW);
  x = 0;
  }
//___________________________________________



}
