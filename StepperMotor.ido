//STEPPER MOTOR TEST PROGRAM
//written in C for Arduino IDE
//Adam Berg, November 19th, 2017
//Driver to test the function of the stepper motor (lower levels of the code), as the upper levels of the code are not yet developped.

#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_MS_PWMServoDriver.h"
Adafruit_MotorShield AFMS = Adafruit_MotorShield();
Adafruit_StepperMotor *motor = AFMS.getStepper(200, 2);
int x = 0;
int Step1 = 3100;
int Step2 = 3100;

void setup()
{
  AFMS.begin(); 
  Serial.begin(9600);
}


void loop()
{
  if (Serial.available())
  {
    x = (Serial.read()- '0');
      if(x == 0)
      {
      //do nothing
      }
      if(x == 1)  //First preset, 2.5 minute slide
      {
        motor->setSpeed(20);
        x = 0;
        run(Step1);
      }
      if(x == 2) //Second preset, 5 minute slide 
      {
        motor->setSpeed(10);
        x = 0;
        run(Step1);
      }
      if(x == 3) //VIDEO PRESET, 5 minute slide for video
      {
        motor->setSpeed(10);
        x = 0;
        motor->step(Step1,FORWARD,SINGLE);
        delay(5000);
         //RETURN BACK TO STARTING LOCATION (opposite side of motor)
        motor->setSpeed(10);
        motor->step(Step2,BACKWARD,SINGLE);
      }
  }
delay(500);
}

void run(int Steps)
{
    motor->step(Steps,FORWARD,MICROSTEP);
    delay(5000);
    //RETURN BACK TO STARTING LOCATION (opposite side of motor)
    motor->setSpeed(10);
    motor->step(Step2,BACKWARD,SINGLE);
}
