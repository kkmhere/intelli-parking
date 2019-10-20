import RPi.GPIO as IO
import time as time

IO.setmode(IO.BCM)
IO.setup(18, IO.OUT)
IO.setup(17, IO.OUT)
IO.output(17,True)

servo = IO.PWM(18,500)
servo.start(40)
try:
   
     for dc in range(40,76,2):
        servo.ChangeDutyCycle(dc)
        time.sleep(0.1)
     time.sleep(4)
     for dc in range(75,39,-2):
        servo.ChangeDutyCycle(dc)
        time.sleep(0.1)
except KeyboardInterrupt:
   pass
servo.stop()
