import cv2
import RPi.GPIO as IO
import time
import imutils
import numpy as np
import pytesseract
import pyrebase
import lcddriver
from PIL import Image

IO.setwarnings(False)
IO.setmode(IO.BCM)
IO.setup(14,IO.IN) #GPIO 14 -> IR sensor as input
IO.setup(21,IO.OUT)
IO.output(21,True)
IO.setup(18, IO.OUT)
IO.setup(17, IO.OUT)
IO.output(17,True)

def gate():
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

def capture():
    cam = cv2.VideoCapture(1)
    ret, image = cam.read()
    if ret:
        cv2.imshow('SnapshotTest',image)
        cv2.destroyWindow('SnapshotTest')
        cv2.imwrite('/home/pi/Desktop/I parking/License-Plate-Recognition-using-Raspberry-Pi/SnapshotTest.jpg',image)
    cam.release()
    

def plate():
    img = cv2.imread('/home/pi/Desktop/I parking/License-Plate-Recognition-using-Raspberry-Pi/4.jpg',cv2.IMREAD_COLOR)

    img = cv2.resize(img, (620,480) )


    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) #convert to grey scale
    gray = cv2.bilateralFilter(gray, 11, 17, 17) #Blur to reduce noise
    edged = cv2.Canny(gray, 30, 200) #Perform Edge detection

    # find contours in the edged image, keep only the largest
    # ones, and initialize our screen contour
    cnts = cv2.findContours(edged.copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    cnts = imutils.grab_contours(cnts)
    cnts = sorted(cnts, key = cv2.contourArea, reverse = True)[:10]
    screenCnt = None

    # loop over our contours
    for c in cnts:
        # approximate the contour
        peri = cv2.arcLength(c, True)
        approx = cv2.approxPolyDP(c, 0.018 * peri, True)
 
        # if our approximated contour has four points, then
        # we can assume that we have found our screen
        if len(approx) == 4:
            screenCnt = approx
            break



    if screenCnt is None:
        detected = 0
        print("No contour detected")
        return 0
    else:
        detected = 1

    if detected == 1:
        cv2.drawContours(img, [screenCnt], -1, (0, 255, 0), 3)

    # Masking the part other than the number plate
    mask = np.zeros(gray.shape,np.uint8)
    new_image = cv2.drawContours(mask,[screenCnt],0,255,-1,)
    new_image = cv2.bitwise_and(img,img,mask=mask)

    # Now crop
    (x, y) = np.where(mask == 255)
    (topx, topy) = (np.min(x), np.min(y))
    (bottomx, bottomy) = (np.max(x), np.max(y))
    Cropped = gray[topx:bottomx+1, topy:bottomy+1]



    #Read the number plate
    text = pytesseract.image_to_string(Cropped, config='--psm 11')
    print("Detected Number is:",text)

    cv2.imshow('image',img)
    cv2.imshow('Cropped',Cropped)
    #cv2.waitKey(0)
    
    cv2.destroyAllWindows()
    return text



    

display = lcddriver.lcd()


config = {
    "apiKey": "AIzaSyCKfgmNcqOUB5m4Utegwexqy7C-u7DRgm8",
    "authDomain": "parking-ee898.firebaseapp.com",
    "databaseURL": "https://parking-ee898.firebaseio.com",
    "storageBucket": "parking-ee898.appspot.com"
    # "serviceAccount": "path/to/serviceAccountCredentials.json"
    
}
firebase = pyrebase.initialize_app(config)

while True:
    if(IO.input(14)==True): #object is far away
        db = firebase.database()
        print("Capturing")
        #capture()
        display.lcd_display_string("    iParking", 1)
        display.lcd_display_string("", 2)
        print("Image captured")
        print("Getting the number plate if any")
        platenumber = plate()
        print("Got the plate number")
        print(platenumber)
        users = db.child("details/car no").get()
        print(users.val())
        if(users.val() == platenumber):
            print("Same")
            slot=db.child("details/slot").get()
            print(slot.val())
            display.lcd_display_string("Your Slot is:", 1)
            display.lcd_display_string(str(slot.val()), 2)
            gate()
            time.sleep(3)
            display.lcd_clear()
        else:
            print("not same")
            
            display.lcd_display_string("    iParking", 1)
            display.lcd_display_string("", 2)
    #print("Printing on the LCD")
    #slot1new=str(slot1.val())
    #display.lcd_display_string("Free Slots :", 1)
    #display.lcd_display_string(slot1new, 2)
        time.sleep(10)
    else:
        print("No Car Detected")
        
        display.lcd_display_string("    iParking", 1)
        display.lcd_display_string("", 2)


