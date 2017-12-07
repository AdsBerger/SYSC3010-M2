#Controller RPI to recieve requests from Headless Rpi 
# also capable of taking direct user requests through buttons
#
#
import RPi.GPIO as GPIO
import time
import serial
import socket
import sys

GPIO.setmode(GPIO.BCM)

#GPIO for the 4 buttons
GPIO.setup(6,GPIO.IN)
GPIO.setup(13,GPIO.IN)
GPIO.setup(19,GPIO.IN)
GPIO.setup(26,GPIO.IN)

#GPIO for the 3 types of mobile requests
GPIO.setup(16,GPIO.IN)
GPIO.setup(20,GPIO.IN)
GPIO.setup(21,GPIO.IN)
GPIO.setwarnings(False)


####### Serial set-up for Arduino's #############
#
#Instructions for connecting, type ls /dev/tty*  into RPi command before and after, serial cable connection
#Which ever port changes is the name that serial corresponds to must be called me, ie. ACM0
#

##### Sending stepper motor instructions through serial  #########
ser_motor = serial.Serial('/dev/ttyACM0',9600)                

####   Sending intervalometer instructions through serial    ######
ser_interv = serial.Serial('/dev/ttyACM1',9600)             


##### Segment initializing ############
# GPIO ports for the 7seg pins
segments =  (11,4,23,8,7,10,18,25)
# top centre, top right, bot right, bot centre, bot left, top left, centre centre, dot points
 
for segment in segments:
    GPIO.setup(segment, GPIO.OUT)
    GPIO.output(segment, 1)
 
#22 is the firt digit, 27 is the second digit, 17 is 3rd, 4th is 24
digits = (22,27,17,24)
# 7seg_digit_pins (12,9,8,6) digits 0-3 respectively


 #PASSING A 1 allows each digit to reflect a number
for digit in digits:
    GPIO.setup(digit, GPIO.OUT)
    GPIO.output(digit, 1)

num = {' ':(0,0,0,0,0,0,0,0),
    '0':(0,0,0,0,0,0,1,1),    
    '1':(1,0,0,1,1,1,1,1),    
    '2':(0,0,1,0,0,1,0,1),    
    '3':(0,0,0,0,1,1,0,1),    
    '4':(1,0,0,1,1,0,0,1),    
    '5':(0,1,0,0,1,0,0,1),    
    '6':(0,1,0,0,0,0,0,1),  
    '7':(0,0,0,1,1,1,1,1),    
    '8':(0,0,0,0,0,0,0,1),  
    '9':(0,0,0,0,1,0,0,1),     
    's':(0,1,0,0,1,0,0,1),
    'c':(0,1,1,0,0,0,1,0),
    'd':(1,0,0,0,0,1,0,0),
    'v':(1,0,0,0,0,0,1,1),
    'p':(0,0,1,1,0,0,0,1),
    'e':(0,1,1,0,0,0,0,1),
    'n':(0,0,0,1,0,0,1,0),
    'h':(1,0,0,1,0,0,0,0)}
# Unique characters can be configured upon need


try:
    s = ('0pen')
    current_length = ('0')
    
    while True:


        for digit in range(4):
            for loop in range(0,8):
                GPIO.output(segments[loop], num[s[digit]][loop])
            GPIO.output(digits[digit],1)
            time.sleep(0.001)
            GPIO.output(digits[digit],0)
            
            
# Input Values from buttons 

        IptVal_one  = GPIO.input(6)
        IptVal_two  = GPIO.input(13)
        IptVal_three = GPIO.input(19)
        IptVal_four  = GPIO.input(26)
        
        MobileRequest1 = GPIO.input(16)
        MobileRequest2 = GPIO.input(20)
        MobileRequest3 = GPIO.input(21)
        
        
        if IptVal_one == False:
            print("Button 1 pressed")
            s = ('sc05')
            current_length = ('1') #PRESET 1, 5 second TL -> 2.5 minute slide
            time.sleep(0.2)
            while IptVal_one == False:
                IptVal_one = GPIO.input(6)

        if IptVal_two == False:
            print("Button 2 pressed")
            s = ('sc10')
            current_length = ('2')  #PRESET 2, 10 second TL -> 5 minute slide
            time.sleep(0.2)
            while IptVal_two == False:
                IptVal_two = GPIO.input(13)
            
        if IptVal_three == False:
            print("Button 3 pressed")
            s = ('v1d5')
            current_length = ('3')
            time.sleep(0.2)
            while IptVal_three == False:
                IptVal_three = GPIO.input(19)
                current_length = ('0')

        if MobileRequest1 == True:
            current_length = ('1')

        if MobileRequest2 == True:
            current_length = ('2')
            
        if MobileRequest3 == True:
            current_length = ('3')
            
#Submit Request button
       if current_length != '0':
            if IptVal_four == False:
                print("Button 4 pressed")
                if current_length == ('3'):
                    print("Change camera into video mode")
                ser_motor.write( current_length )
                ser_interv.write( current_length )
                time.sleep(200)
                current_length = ('0')
                time.sleep(200)
                while IptVal_four == False:
                    IptVal_four = GPIO.input(26)
            
finally:
    GPIO.cleanup()              


        


