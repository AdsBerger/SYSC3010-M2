import socket
import sys
import time
import RPi.GPIO as GPIO
soc = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = 8006
server_address = ('',port)
soc.bind(server_address)


#On receiving of requests from mobile device, a GPIO pin is set high to notify Controller RPI of request
GPIO.setmode(GPIO.BCM)
GPIO.setup(13,GPIO.OUT, initial = 0)
GPIO.setwarnings(False)

while True:
    
    print("Waiting to receive on port: %d" %port)
    buf, address = soc.recvfrom(port)
    if not len(buf):
        break
    print("Input: " + buf)
    if(buf == "5 seconds"):
        GPIO.output(13,1)
        time.sleep(.5)
        GPIO.output(13,0)
        print("Sent!")
        
soc.shutdown(1)
GPIO.cleanup()
