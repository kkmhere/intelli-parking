import RPi.GPIO as IO
import time
IO.setwarnings(False)
IO.setmode(IO.BCM)


IO.setup(14,IO.IN) #GPIO 14 -> IR sensor as input

while 1:

    if(IO.input(14)==True): #object is far away
        print("working")
    
    if(IO.input(14)==False): #object is near
        
        print("woring yesss")